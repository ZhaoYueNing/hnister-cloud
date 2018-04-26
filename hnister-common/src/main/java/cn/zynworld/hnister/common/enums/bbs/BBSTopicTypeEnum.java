package cn.zynworld.hnister.common.enums.bbs;

/**
 * @auther Buynow Zhao
 * @create 2018/4/23
 */
public enum  BBSTopicTypeEnum {
	GENERAL_TYPE(1,"普通类型");

	private Integer code;
	private String desc;

	BBSTopicTypeEnum(Integer code, String desc) {
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
