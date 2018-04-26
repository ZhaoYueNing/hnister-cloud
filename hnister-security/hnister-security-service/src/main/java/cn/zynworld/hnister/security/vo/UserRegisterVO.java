package cn.zynworld.hnister.security.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/7
 */
@Data
public class UserRegisterVO {

	private String username;

	private Integer zlassId;

	private String name;

	private Integer gender;

	private String password;

}
