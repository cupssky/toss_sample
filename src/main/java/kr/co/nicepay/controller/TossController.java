package kr.co.nicepay.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.nicepay.model.response.ApiResult;
import kr.co.nicepay.service.TossService;
import lombok.RequiredArgsConstructor;

@Api(value = "토스 API 정보")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class TossController {

  private final TossService tossService;
  
  @ApiOperation(
      httpMethod = "POST",
      value = "결제 승인",
      notes = "결제 승인",
      response = ApiResult.class
  )
  @ApiImplicitParams({
    @ApiImplicitParam(name = "paymentKey", value = "결제승인키", required = true, dataType = "string", defaultValue = "5zJ4xY7m0kODnyRpQWGrN2xqGlNvLrKwv1M9ENjbeoPaZdL7"),
    @ApiImplicitParam(name = "orderId", value = "주문ID", required = true, dataType = "string", defaultValue = "1000000100068"),
    @ApiImplicitParam(name = "amount", value = "금액", required = true, dataType = "string", defaultValue = "19000")
  })
  @PostMapping("/approval")
  public ApiResult requestApproval(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam String amount) {
    return ApiResult.success(tossService.requestApproval(paymentKey, orderId, amount));
  }
  
  @ApiOperation(
      httpMethod = "POST",
      value = "결제 취소",
      notes = "결제 취소",
      response = ApiResult.class
  )
  @ApiImplicitParams({
    @ApiImplicitParam(name = "paymentKey", value = "결제승인키", required = true, dataType = "string", defaultValue = "5zJ4xY7m0kODnyRpQWGrN2xqGlNvLrKwv1M9ENjbeoPaZdL7"),
    @ApiImplicitParam(name = "cancelReason", value = "결제취소사유", required = true, dataType = "string", defaultValue = "테스트 취소"),
  })
  @PostMapping("/cancel")
  public ApiResult requestCancel(@RequestParam String paymentKey, @RequestParam String cancelReason) {
    return ApiResult.success(tossService.requestCancel(paymentKey, cancelReason));
  }
  
  @ApiOperation(
      httpMethod = "GET",
      value = "결제 조회",
      notes = "결제 조회",
      response = ApiResult.class
  )
  @ApiImplicitParams({
    @ApiImplicitParam(name = "paymentKey", value = "결제승인키", required = true, dataType = "string", defaultValue = "5zJ4xY7m0kODnyRpQWGrN2xqGlNvLrKwv1M9ENjbeoPaZdL7"),
  })
  @GetMapping("/query")
  public ApiResult requestQuery(@RequestParam String paymentKey) {
    return ApiResult.success(tossService.requestQuery(paymentKey));
  }
}
