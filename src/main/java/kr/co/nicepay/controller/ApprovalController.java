package kr.co.nicepay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApprovalController {


  @PostMapping("/approval")
  public String requestApproval(@RequestParam String paymentKey) {
    return null;
  }
}
