package cn.zynworld.hnister.hnisterbbsservice.dto;

import lombok.Data;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/24
 */
@Data
public class BBSTierPostDTO {

	private Long topicId;

	private String username;

	private Integer type;

	private Integer status;

	private String content;
}
