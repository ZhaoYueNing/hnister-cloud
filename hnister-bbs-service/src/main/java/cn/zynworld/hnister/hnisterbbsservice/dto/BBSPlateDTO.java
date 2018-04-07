package cn.zynworld.hnister.hnisterbbsservice.dto;

import cn.zynworld.hnister.common.domain.BBSTheme;
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
