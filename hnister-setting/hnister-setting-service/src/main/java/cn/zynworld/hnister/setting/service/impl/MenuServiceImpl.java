package cn.zynworld.hnister.setting.service.impl;

import cn.zynworld.hnister.setting.domain.Menu;
import cn.zynworld.hnister.setting.domain.MenuExample;
import cn.zynworld.hnister.setting.mappers.MenuGroupMapper;
import cn.zynworld.hnister.setting.mappers.MenuMapper;
import cn.zynworld.hnister.setting.convertors.MenuConvertors;
import cn.zynworld.hnister.setting.dto.MenuDTO;
import cn.zynworld.hnister.setting.service.MenuService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther Buynow Zhao
 * @create 2018/3/23
 */
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private MenuGroupMapper menuGroupMapper;

	@Override
	public MenuDTO findMenuTreeByGroupId(Integer groupId) {
		//构建菜单的根元素
		MenuDTO menuDTO = MenuDTO.builder().id(0).groupId(groupId).tier(0).build();
		Map<Integer, List<MenuDTO>> menuMap = getMenuDTOMapByGroup(groupId);
		menuAddChildren(menuDTO,menuMap);
		return menuDTO;
	}

	@Override
	public List<MenuDTO> findMenuListByGroupId(Integer groupId) {
		List<MenuDTO> menuList = Lists.newArrayList();

		Map<Integer, List<MenuDTO>> menuMap = getMenuDTOMapByGroup(groupId);
		addMenuToList(0, 1,menuList, menuMap);
		return menuList;
	}




	@Override
	@Transactional
	public boolean add(Menu menu) {
		int result = menuMapper.insert(menu);
		return result > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		Menu menu = menuMapper.selectByPrimaryKey(id);
		if (menu == null) {
			return false;
		}
		Map<Integer, List<MenuDTO>> menuMap = getMenuDTOMapByGroup(menu.getGroupId());
		deleteMenuTree(id,menuMap);

		return true;
	}

	@Override
	public boolean update(Menu menu) {
		int result = menuMapper.updateByPrimaryKey(menu);
		return result > 0;
	}




	//为menuDTO 添加子菜单
	private void menuAddChildren(MenuDTO parentMenu, Map<Integer, List<MenuDTO>> menuMap) {
		if (parentMenu == null || parentMenu.getId() == null) {
			return;
		}
		Integer parentId = parentMenu.getId();
		if (!menuMap.containsKey(parentId)) {
			return;
		}
		if (parentMenu.getChildren() == null) {
			parentMenu.setChildren(Lists.newArrayList());
		}
		List<MenuDTO> menuList = menuMap.get(parentId);
		menuList.stream().forEach(menu -> {
			//set 层级
			menu.setTier(parentMenu.getTier()+1);
			parentMenu.getChildren().add(menu);
			menuAddChildren(menu,menuMap);
		});
	}

	//通过group ID 获取 key parentID value menu list 的map对象
	private Map<Integer, List<MenuDTO>> getMenuDTOMapByGroup(Integer groupId) {
		MenuExample menuExample = new MenuExample();
		menuExample.createCriteria().andGroupIdEqualTo(groupId);
		//查询并转换为DTO
		List<MenuDTO> menuList = menuMapper.selectByExample(menuExample).stream()
				.map(MenuConvertors::do2dto).collect(Collectors.toList());

		//key parentId   value menuDTO
		Map<Integer,List<MenuDTO>> menuMap = Maps.newHashMap();
		menuList.stream().forEach(menu -> {
			if (!menuMap.containsKey(menu.getParentId())){
				menuMap.put(menu.getParentId(), Lists.newArrayList());
			}
			menuMap.get(menu.getParentId()).add(menu);
		});
		return menuMap;
	}

	//遍历删除菜单及其子节点
	private boolean deleteMenuTree(Integer id, Map<Integer, List<MenuDTO>> menuMap) {
		if (menuMap.containsKey(id)) {
			menuMap.get(id).forEach(menu ->{
				deleteMenuTree(menu.getId(),menuMap);
			});
		}
		return menuMapper.deleteByPrimaryKey(id) > 0;
	}

	//将该组菜单按顺序插入list中
	private void addMenuToList(Integer parentId,Integer tier, List<MenuDTO> menuList, Map<Integer, List<MenuDTO>> menuMap) {
		if (!menuMap.containsKey(parentId)) {
			return;
		}
		List<MenuDTO> currentParentIdMenuList = menuMap.get(parentId);
		for (MenuDTO menu : currentParentIdMenuList) {
			menuList.add(menu);
			menu.setTier(tier);
			addMenuToList(menu.getId(),tier+1,menuList,menuMap);
		}
	}


}
