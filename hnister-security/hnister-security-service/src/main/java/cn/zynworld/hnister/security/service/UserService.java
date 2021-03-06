package cn.zynworld.hnister.security.service;

import cn.zynworld.hnister.security.domain.User;
import cn.zynworld.hnister.security.domain.UserExample;
import cn.zynworld.hnister.security.mappers.UserMapper;
import cn.zynworld.hnister.common.service.BaseAbstractService;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.schoolmate.api.ZlassRestApi;
import cn.zynworld.hnister.schoolmate.api.dto.ZlassDTO;
import cn.zynworld.hnister.security.exception.CreateUserException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther Buynow Zhao
 * @create 2018/4/7
 */
@Service
public class UserService extends BaseAbstractService<User,String,UserMapper,UserExample> {

	@Autowired
	private ZlassRestApi zlassRestApi;

	/**
	 * 添加用户，检查用户各字段是否符合要求，自动加密存储salt
	 * @param user
	 * @return
	 */
	@Transactional
	public boolean add(User user) throws CreateUserException {
		//check
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) ||
				user.getPassword().length() < 8 || user.getUsername().length() < 8) {
			throw new CreateUserException("用户名及密码长度必须大于8");
		}
		//salt
		String salt = CodecUtils.getSalt();
		user.setSalt(salt);
		user.setPassword(CodecUtils.getSaltPassword(user.getPassword(),user.getSalt()));
		//为user 冗余专业、年级、学院信息
		if (user.getZlassId() != null) {
			ZlassDTO zlass = zlassRestApi.findById(user.getZlassId());
			if (zlass != null) {
				user.setSpecialtyId(zlass.getSpecialtyId());
				user.setCollegeId(zlass.getCollegeId());
				user.setGrad(zlass.getGrade());
			}else{
				user.setZlassId(null);
			}
		}

		//add
		Integer result = baseAdd(user);
		return result > 0;
	}

}
