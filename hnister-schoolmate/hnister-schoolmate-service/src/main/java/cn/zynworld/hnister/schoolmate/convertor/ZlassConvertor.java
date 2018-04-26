package cn.zynworld.hnister.schoolmate.convertor;

import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.schoolmate.api.dto.ZlassDTO;
import cn.zynworld.hnister.schoolmate.domain.Zlass;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
public class ZlassConvertor {

	public static ZlassDTO do2dto(Zlass zlass) {
		return BeanUtils.copyProperties(zlass, new ZlassDTO());
	}
}
