package cn.zynworld.hnister.common.enums.bbs;

/**
 * @auther Buynow Zhao
 * @create 2018/4/23
 */
public enum BBSTopicStautsEnum {
	GENERAL_STATUS(1,"普通状态");

	private Integer code;
	private String desc;

	BBSTopicStautsEnum(Integer code, String desc) {
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
