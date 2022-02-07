package kr.co.nicepay.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * <b>RegisterInfo</b>
 * 
 * <pre>
 * RegisterInfo
 * </pre>
 * <hr>
 * 
 * @author tgyun615
 * 
 * @version 0.1 2019. 9. 16
 */
@Data
public class RegisterInfo {

  private Date registerDatetime;
  @JsonIgnore
  private Date modifyDatetime;
}
