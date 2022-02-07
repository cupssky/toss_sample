package kr.co.nicepay.exception;

import kr.co.nicepay.model.types.ApiResultType;
import lombok.Getter;

/**
 * <b>ApiException</b>
 * 
 * <pre>
 * ApiException
 * </pre>
 * <hr>
 * 
 * @author tgyun615
 * 
 * @version 0.1 2019. 9. 6
 */
@Getter
public class ApiException extends RuntimeException {

  private final ApiResultType apiResultType;

  public ApiException(String message, ApiResultType apiResultType) {
    super(message);
    this.apiResultType = apiResultType;
  }
}
