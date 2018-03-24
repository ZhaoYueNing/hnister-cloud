package cn.zynworld.hnister.setting.api;

import cn.zynworld.hnister.common.domain.Menu;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.setting.dto.MenuDTO;
import cn.zynworld.hnister.setting.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/3/23
 */
@RestController
@RequestMapping(path = "rest")
public class MenuApi {
	@Autowired
	private MenuService menuService;

	/**
	 * 通过组ID 获取该组菜单的树结构
	 * @param groupId
	 * @return
	 */
	@GetMapping("pb/menuTree/groupId/{groupId}")
	public MenuDTO findTreeByGroupId(@PathVariable Integer groupId) {
		return menuService.findMenuTreeByGroupId(groupId);
	}

	@GetMapping("pb/menuList/groupId/{groupId}")
	public List<MenuDTO> findListByGroupId(@PathVariable Integer groupId) {
		return menuService.findMenuListByGroupId(groupId);
	}




	@PostMapping("pt/menu")
	public ResultBean add(@RequestBody Menu menu) {
		boolean result = menuService.add(menu);
		return ResultBean.create(result);
	}

	@DeleteMapping(path = "pt/menu/{id}")
	public ResultBean deleteById(@PathVariable Integer id) {
		boolean result = menuService.deleteById(id);
		return ResultBean.create(result);
	}

	@PutMapping(path = "pt/menu")
	public ResultBean update(@RequestBody Menu menu) {
		boolean result = menuService.update(menu);
		return ResultBean.create(result);
	}
}
