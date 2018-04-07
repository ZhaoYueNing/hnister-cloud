package cn.zynworld.hnister.security.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/7
 */
@Data
@Builder
public class UserRegisterVO {

	private String username;

	private Integer zlassId;

	private String name;

	private Date birth;

	private Integer gender;

	private String city;

	private String password;

	private String avatar;

	private Integer specialtyId;

	private Integer collegeId;

	private Integer grad;
}
