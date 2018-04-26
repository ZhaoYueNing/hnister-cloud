package cn.zynworld.hnister.bbs.dto;

import cn.zynworld.hnister.bbs.domain.BBSTheme;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/4
 *
 * 包含Theme
 */
@Data
@Builder
public class BBSPlateDTO {
	private Integer id;
	private String name;
	private String remark;
	private List<BBSTheme> themes;
}
