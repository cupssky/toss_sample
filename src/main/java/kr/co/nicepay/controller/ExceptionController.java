package kr.co.nicepay.controller;

import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import kr.co.nicepay.exception.ApiException;
import kr.co.nicepay.model.response.ApiResult;
import kr.co.nicepay.model.types.ApiResultType;
import lombok.extern.slf4j.Slf4j;

/**
 * <b>ExceptionController</b>
 * 
 * <pre>
 * ExceptionController
 * </pre>
 * <hr>
 * 
 * @author tgyun615
 * 
 * @version 0.1 2019. 9. 23
 */
@Slf4j
@RestControllerAdvice("kr.co.nicepay.controller")
public class ExceptionController {

  private static final ApiResult UNHANDLED_ERROR_RESPONSE = ApiResult.of(ApiResultType.failed);

  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResult unhandledException(Throwable throwable) {
    log.error(throwable.getMessage(), throwable);
    return UNHANDLED_ERROR_RESPONSE;
  }

  @ExceptionHandler(ApiException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResult apiException(ApiException e) {
    log.info(e.getMessage(), e);
    return ApiResult.of(e.getApiResultType());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResult validationException(ConstraintViolationException e) {
    log.error(e.getMessage(), e);

    return e.getConstraintViolations().stream().findFirst().map(constraintViolation -> {
      String message = constraintViolation.getMessage();
      try {
        return ApiResultType.valueOf(message);
      } catch (IllegalArgumentException e1) {
        return null;
      }
    }).map(apiResultType -> ApiResult.of(apiResultType)).orElseGet(() -> {
      // ConstraintViolationException으로 넘어온 message가 미지 정의된 ApiResultType이 아니라면 unhandled exception과
      // 동일한 응답을 내린다.
      log.error(e.getMessage(), e);
      return UNHANDLED_ERROR_RESPONSE;
    });
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResult validationException(MethodArgumentNotValidException e) {
    log.error(e.getMessage(), e);

    try {
      // MethodArgumentNotValidException으로 넘어온 message가 미지 정의된 ApiResultType이 아니라면 unhandled
      // exception과 동일한 응답을 내린다.
      return ApiResult.of(
          ApiResultType.valueOf(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    } catch (IllegalArgumentException e1) {
      return UNHANDLED_ERROR_RESPONSE;
    }
  }
}
