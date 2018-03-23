package cn.zynworld.hnister.setting.api;

import cn.zynworld.hnister.common.domain.Menu;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.setting.dto.MenuDTO;
import cn.zynworld.hnister.setting.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther Buynow Zhao
 * @create 2018/3/23
 */
@RestController
@RequestMapping(path = "rest")
public class MenuApi {
	@Autowired
	private MenuService menuService;

	@GetMapping("pb/menu/{groupId}")
	public MenuDTO findByGroupId(@PathVariable Integer groupId) {
		return menuService.findMenuTreeByGroupId(groupId);
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
}
