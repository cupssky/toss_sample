package kr.co.nicepay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }
  
  @RequestMapping("/success")
  public String success() {
    return "success";
  }
  
  @RequestMapping("/iamport")
  public String iamport() {
    return "iamport";
  }
}
