package cn.zynworld.hnister.security.convertor;

import cn.zynworld.hnister.security.domain.User;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.security.vo.UserRegisterVO;

/**
 * @auther Buynow Zhao
 * @create 2018/4/7
 */
public class UserConvertor {

	public static User registerVO2DO(UserRegisterVO userRegisterVO) {
		return BeanUtils.copyProperties(userRegisterVO,new User());
	}
}
