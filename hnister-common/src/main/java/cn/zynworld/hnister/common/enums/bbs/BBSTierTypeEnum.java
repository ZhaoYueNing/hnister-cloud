package cn.zynworld.hnister.common.enums.bbs;

public enum BBSTierTypeEnum {
	GENERAL_TYPE(1,"普通类型");

	private Integer code;
	private String desc;

	BBSTierTypeEnum(Integer code, String desc) {
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
