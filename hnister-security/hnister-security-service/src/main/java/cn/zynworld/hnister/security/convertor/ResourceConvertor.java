package cn.zynworld.hnister.security.convertor;

import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.security.api.dto.ResourceDTO;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
public class ResourceConvertor {

	public static ResourceDTO do2dto(Resource resource) {
		return BeanUtils.copyProperties(resource, new ResourceDTO());
	}
}
