package kr.co.nicepay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * <b>ValidationConfig</b>
 * 
 * <pre>
 * Validation 컨피그
 * </pre>
 * <hr>
 * 
 * @author tgyun615
 * 
 * @version 0.1 2019. 9. 6
 */
@Configuration
public class ValidationConfig {
  // RequestParam에서 validation 동작을 위한 빈 등록
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }
}
