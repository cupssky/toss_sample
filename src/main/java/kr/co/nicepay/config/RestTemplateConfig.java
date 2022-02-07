package kr.co.nicepay.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class RestTemplateConfig {
  private static final int CONNECT_TIMEOUT = 15000; // 15 sec.
  private static final int READ_TIMEOUT = 20000; // 20 sec.
  
  @Value("${spring.profiles.active}")
  private String profile;

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  }

  @Bean
  public ClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
        HttpClientBuilder.create().setConnectionManager(new PoolingHttpClientConnectionManager() {{
          setDefaultMaxPerRoute(20);
          setMaxTotal(200);
        }}).build());
    factory.setConnectTimeout(CONNECT_TIMEOUT);
    factory.setReadTimeout(READ_TIMEOUT);

    return factory;
  }

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    messageConverter.setPrettyPrint(false);
    messageConverter.setObjectMapper(objectMapper());
    restTemplate.getMessageConverters().removeIf(
        m -> m.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
    restTemplate.getMessageConverters().add(messageConverter);

    if (!"release".equals(profile)) {
      List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
      interceptorList.add((httpRequest, bytes, clientHttpRequestExecution) -> {
        log.debug(
            "===========================request begin================================================");
        log.debug("URI         : " + httpRequest.getURI());
        log.debug("Method      : " + httpRequest.getMethod());
        log.debug("Headers     : " + httpRequest.getHeaders());
        log.debug("Request body: " + new String(bytes, "UTF-8"));
        log.debug(
            "==========================request end================================================");

        return clientHttpRequestExecution.execute(httpRequest, bytes);
      });

      restTemplate.setInterceptors(interceptorList);
    }

    return restTemplate;
  }

}
