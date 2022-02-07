package kr.co.nicepay.util;

import org.apache.xerces.impl.dv.util.Base64;

public class KeyConvertUtil {

  public static String keyConvertBase64(String key) {
    return Base64.encode(key.getBytes()).toString();
  }
}
