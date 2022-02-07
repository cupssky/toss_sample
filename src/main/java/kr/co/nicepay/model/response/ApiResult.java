package kr.co.nicepay.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import kr.co.nicepay.model.types.ApiResultType;
import lombok.Data;

/**
 * <b>ApiResult<T></b>
 * 
 * <pre>
 * ApiResult 정보
 * </pre>
 * <hr>
 * 
 * @version 1.0 2019. 9. 6.
 * 
 * @author tgyun615
 * 
 */
@Data
public class ApiResult<T> {

  private int code;
  @JsonInclude(Include.NON_NULL)
  private String message;
  @JsonInclude(Include.NON_NULL)
  private T result;

  private ApiResult(ApiResultType apiResultType, String message, T result) {
    this.code = apiResultType.getCode();
    this.message = message;
    this.result = result;
  }

  public static <T> ApiResult<T> success(T result) {
    return new ApiResult<>(ApiResultType.success, ApiResultType.success.getMessage(), result);
  }

  public static <T> ApiResult<T> success() {
    return new ApiResult<>(ApiResultType.success, ApiResultType.success.getMessage(), null);
  }

  public static ApiResult of(ApiResultType status) {
    return new ApiResult<>(status, status.getMessage(), null);
  }

  public static <T> ApiResult<T> of(ApiResultType status, T result) {
    return new ApiResult<>(status, status.getMessage(), result);
  }
}
