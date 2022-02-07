package kr.co.nicepay.model.types;

import lombok.Getter;

/**
 * <b>ApiResultType</b>
 * 
 * <pre>
 * ApiResult 목록
 * </pre>
 * <hr>
 * 
 * @version 1.0 2019. 9. 6.
 * 
 * @author tgyun615
 * 
 */
@Getter
public enum ApiResultType {
  success(1, "success"),
 
  bad_request(400, "bad_request"),
  
  invalid_process_status(435, "invalid_process_status"),
  invalid_email(436, "invalid_email"),
  invalid_type(438, "invalid_type"),
  invalid_status(439, "invalid_status"),
  invalid_code_level(440, "invalid_code_level"),
  invalid_stage(441, "invalid_stage"),
  invalid_phone_number(442, "invalid_phone_number"),
  invalid_paymethod_use(443, "invalid_paymethod_use"),
  invalid_alert(444, "invalid_alert"),
  
  duplicated_phone_number(457, "duplicated_phone_number"),
  duplicated_user(458, "duplicated_user"),
  duplicated_chat_id(459, "duplicated_chat_id"),
  duplicated_code_chat(460, "duplicated_code_chat"),
 
  not_existed_merchant(464, "not_existed_merchant"),
  not_existed_merchant_message(465, "not_existed_merchant_message"),
  not_existed_merchant_message_key(466, "not_existed_merchant_message_key"),
  not_existed_message_content(467, "not_existed_message_content"),
  not_existed_type(468, "not_existed_type"),
  not_existed_status(469, "not_existed_status"),
  not_existed_user(470, "not_existed_user"),
  not_existed_member_id(471, "not_existed_member_id"),
  not_existed_member_name(472, "not_existed_member_name"),
  not_existed_tel_number(473, "not_existed_tel_number"),
  not_existed_group(474, "not_existed_group"),
  not_existed_group_id(475, "not_existed_group_id"),
  not_existed_group_name(476, "not_existed_group_name"),
  not_existed_chat_id(477, "not_existed_chat_id"),
  not_existed_code_id(478, "not_existed_code_id"),
  not_existed_sub_code_id(479, "not_existed_sub_code_id"),
  not_existed_level(480, "not_existed_level"),
  not_existed_email(481, "not_existed_email"),
  not_existed_stage(481, "not_existed_stage"),
  not_existed_phone_number(482, "not_existed_phone_number"),
  not_existed_card_use(483, "not_existed_card_use"),
  not_existed_bank_use(484, "not_existed_bank_use"),
  not_existed_vbank_use(485, "not_existed_vbank_use"),
  not_existed_cellphone_use(486, "not_existed_cellphone_use"),
  not_existed_alert_type(487, "not_existed_alert_type"),
  not_existed_band_post_key(488, "not_existed_band_post_key"),
  not_existed_paymethod_service_code(489, "not_existed_paymethod_service_code"),
  not_existed_paymethod_type(490, "not_existed_paymethod_type"),
  not_existed_paymethod_service_name(491, "not_existed_paymethod_service_name"),
  
  failed(500, "failed"),
  failed_not_look_up_code(501, "failed_not_look_up_code"),
  failed_not_look_up_chat(502, "failed_not_look_up_chat"),
  failed_not_look_up_member(503, "failed_not_look_up_member"),
  failed_not_look_up_group(504, "failed_not_look_up_group"),
  failed_not_look_up_sms_auth(505, "failed_not_look_up_sms_auth"),
  
  bad_message(550, "bad_message"),
  
  invalid_length_member_id(700, "invalid_length_member_id"),
  invalid_length_member_name(701, "invalid_length_member_name"),
  invalid_length_group_id(702, "invalid_length_group_id"),
  invalid_length_group_name(703, "invalid_length_group_name"),
  invalid_length_chat_id(704, "invalid_length_chat_id"),
  invalid_length_code_id(705, "invalid_length_code_id"),
  invalid_length_sub_code_id(706, "invalid_length_sub_code_id"),
  invalid_length_message_content(707, "invalid_length_message_content"),
  invalid_length_code_name(708, "invalid_length_code_name"),
  invalid_length_sub_code_name(709, "invalid_length_sub_code_name"),
  

  existed_merchant_message_key(800, "existed_merchant_message_key"),
  
  more_then_existing_code_level(801, "more_then_existing_code_level");
  
	
  private int code;
  private String message;
  
  ApiResultType(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static ApiResultType getTypeByCode(int code) {
    for (ApiResultType e : values()) {
      if (e.getCode() == code) {
        return e;
      }
    }
    return failed;
  }
}
