package cn.zynworld.hnister.security.convertor;

import cn.zynworld.hnister.security.domain.RoleResourceRelaKey;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.security.api.dto.RoleResourceRelaKeyDTO;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
public class RoleResourceRelaKeyConvertor {
	public static RoleResourceRelaKeyDTO do2dto(RoleResourceRelaKey roleResourceRelaKey) {
		return BeanUtils.copyProperties(roleResourceRelaKey, new RoleResourceRelaKeyDTO());
	}
}
