package cn.zynworld.hnister.setting.service;

import cn.zynworld.hnister.common.domain.Menu;
import cn.zynworld.hnister.setting.dto.MenuDTO;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/3/23
 */
public interface MenuService {
	/**
	 * 通过groupId 构建 menuDTO
	 * @param groupId 菜单组的ID
	 * @return 包含子菜单的MenuDTO
	 */
	MenuDTO findMenuTreeByGroupId(Integer groupId);

	boolean add(Menu menu);

	boolean deleteById(Integer id);

	boolean update(Menu menu);

	List<MenuDTO> findMenuListByGroupId(Integer groupId);
}
