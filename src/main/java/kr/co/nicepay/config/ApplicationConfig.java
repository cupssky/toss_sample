package kr.co.nicepay.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <b>ApplicationConfig</b>
 * 
 * <pre>
 * 어플리케이션 컨피그
 * </pre>
 * <hr>
 * 
 * @author tgyun615
 * 
 * @version 0.1 2019. 9. 6
 */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }
}
