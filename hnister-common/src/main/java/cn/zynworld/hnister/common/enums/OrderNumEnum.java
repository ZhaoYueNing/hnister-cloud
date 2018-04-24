package cn.zynworld.hnister.common.enums;

/**
 * 排序级别，值越大优先级越高
 */
public enum OrderNumEnum {
	TOP3_ORDER(103,"高序3"),
	TOP2_ORDER(102,"高序2"),
	TOP1_ORDER(101,"高序1"),
	DEFALUT_ORDER(100,"默认级别"),
	LOW1_ORDER(99,"低序1"),
	LOW2_ORDER(99,"低序2"),
	LOW3_ORDER(99,"低序3"),
	;


	private Integer code;
	private String desc;

	OrderNumEnum(Integer code, String desc) {
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
