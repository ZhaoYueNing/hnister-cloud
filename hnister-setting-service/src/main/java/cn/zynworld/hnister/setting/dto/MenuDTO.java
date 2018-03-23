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

	private Boolean parent;

	private Integer parentId;

	private String name;

	private String remark;

	private String url;

	private Integer type;

	private List<MenuDTO> children;
}
