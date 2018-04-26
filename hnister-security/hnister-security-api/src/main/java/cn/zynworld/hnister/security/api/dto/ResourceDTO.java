package cn.zynworld.hnister.security.api.dto;

import lombok.Data;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
@Data
public class ResourceDTO {
	private Integer id;

	private String name;

	private Integer method;

	private String url;

	private Integer status;

	private Integer groupId;

	private String remark;
}
