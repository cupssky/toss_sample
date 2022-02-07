package kr.co.nicepay.model.types;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum StatusType {
    
    disable("-1", "disable"),
    fail("3", "fail"),
	success("2", "success"),
	INIT("1", "init");
	
	private static final Map<String, StatusType> valueAndTypeMap = new HashMap<>();

	static {
		for (StatusType type : values()) {
			valueAndTypeMap.put(type.getValue(), type);
		}
	}
	
	private String value;
	private String description;
	
	private StatusType(String value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public static StatusType findByValue(String value) {
		return valueAndTypeMap.get(value);
	}
}
