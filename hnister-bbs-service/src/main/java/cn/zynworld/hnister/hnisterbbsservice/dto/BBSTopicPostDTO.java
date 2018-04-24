package cn.zynworld.hnister.hnisterbbsservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/23
 * 发布的VO
 */
@Data
public class BBSTopicPostDTO {
	private Long id;

	private Integer themeId;

	private String title;

	private String username;

	private Integer type;

	private Integer status;

	private String content;

	private Integer orderNum;

	private Date postTime;
}
