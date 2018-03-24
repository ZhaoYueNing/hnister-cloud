package cn.zynworld.hnister.setting.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/3/23
 */
@Data
@Builder
public class MenuDTO {
	private Integer id;

	private Integer groupId;

	private Integer parentId;

	private String name;

	private String remark;

	private String url;

	private Integer type;

	private List<MenuDTO> children;

	//菜单层级 1为一级菜单 2为2级菜单
	private Integer tier;
}
