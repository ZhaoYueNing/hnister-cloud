package cn.zynworld.hnister.setting.convertors;

import cn.zynworld.hnister.common.domain.Menu;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.setting.dto.MenuDTO;

/**
 * @auther Buynow Zhao
 * @create 2018/3/23
 */
public class MenuConvertors {

	public static MenuDTO do2dto(Menu menu) {
		MenuDTO menuDTO = MenuDTO.builder().build();
		BeanUtils.copyProperties(menu,menuDTO);
		return menuDTO;
	}
}
