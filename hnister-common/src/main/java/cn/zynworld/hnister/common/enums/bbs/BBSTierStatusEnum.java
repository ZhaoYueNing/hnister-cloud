package cn.zynworld.hnister.common.enums.bbs;

public enum BBSTierStatusEnum {
	GENERAL_STATUS(1,"正常类型");

	private Integer code;
	private String desc;

	BBSTierStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
