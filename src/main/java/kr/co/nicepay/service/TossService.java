package kr.co.nicepay.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import kr.co.nicepay.exception.ApiException;
import kr.co.nicepay.model.types.ApiResultType;
import kr.co.nicepay.util.JsonConvertUtil;
import kr.co.nicepay.util.KeyConvertUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TossService {

  //TODO 나중에 빼야함, 예시라서
  private static final String SECRET_KEY = "test_ak_OAQ92ymxN34YL5pedZgVajRKXvdk:";
  private final RestTemplate restTemplate;
  private final HttpHeaders headers;
  
  /** 
   * API 정보
   * https://api.tosspayments.com/v1/payments/ 
   * **/  
  @Value("${toss.api}")
  private String TOSS_API_URL;
  
  public TossService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.headers = new HttpHeaders();
    
    headers.add("Authorization", "Basic " + KeyConvertUtil.keyConvertBase64(SECRET_KEY));
    headers.setContentType(MediaType.APPLICATION_JSON);
  }
  
  public Map<String, Object> requestApproval(String paymentKey, String orderId, String amount) {
    Map<String, Object> map = new HashMap<>();
    map.put("orderId", orderId);
    map.put("amount", amount);
    
    HttpEntity<String> request = new HttpEntity<>(JsonConvertUtil.toJson(map), headers);
 
    ResponseEntity<Map> result =
        restTemplate.postForEntity(TOSS_API_URL + paymentKey, request, Map.class);
    
    if(result.getStatusCode() != HttpStatus.OK) {
      throw new ApiException("승인 요청 실패 :: " + result.getBody(), ApiResultType.failed);
    }
    
    return result.getBody();
  }
  
  public Map<String, Object> requestCancel(String paymentKey, String cancelReason) {
    Map<String, Object> map = new HashMap<>();
    map.put("cancelReason", cancelReason);
    
    HttpEntity<String> request = new HttpEntity<>(JsonConvertUtil.toJson(map), headers);
    log.info("요청헤더 확인 : {}", headers); 
    
    ResponseEntity<Map> result =
        restTemplate.postForEntity(TOSS_API_URL + paymentKey + "/cancel", request, Map.class);
     
    if(result.getStatusCode() != HttpStatus.OK) {
      throw new ApiException("취소 요청 실패 :: " + result.getBody(), ApiResultType.failed);
    } 
    
    return result.getBody();
  }
  
  public Map<String, Object> requestQuery(String paymentKey) {
    HttpEntity<String> request = new HttpEntity<>(headers);
    ResponseEntity<Map> result = restTemplate.exchange(TOSS_API_URL + paymentKey, HttpMethod.GET, request, Map.class);        
    
    if(result.getStatusCode() != HttpStatus.OK) {
      throw new ApiException("조회 요청 실패 :: " + result.getBody(), ApiResultType.failed);
    }
    
    return result.getBody();
  }
}
