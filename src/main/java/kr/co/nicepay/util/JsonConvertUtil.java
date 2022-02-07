package kr.co.nicepay.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <b>JsonConvertUtil</b>
 * 
 * <pre>
 * JsonConvertUtil
 * </pre>
 * <hr>
 * 
 * @author tgyun615
 * 
 * @version 0.1 2019. 9. 30
 */
public class JsonConvertUtil {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T fromJson(String data, Class<T> targetClass) {
    try {
      return objectMapper.readValue(data, targetClass);
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException();
    }
  }
}
