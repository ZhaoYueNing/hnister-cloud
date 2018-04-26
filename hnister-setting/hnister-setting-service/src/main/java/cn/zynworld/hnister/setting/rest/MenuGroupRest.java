package cn.zynworld.hnister.setting.rest;

import cn.zynworld.hnister.setting.domain.MenuGroup;
import cn.zynworld.hnister.setting.mappers.MenuGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/3/24
 */
@RestController
@RequestMapping(path = "rest")
public class MenuGroupRest {
	@Autowired
	private MenuGroupMapper groupMapper;

	@GetMapping(path = "pb/menuGroups")
	public List<MenuGroup> findAll() {
		return groupMapper.selectByExample(null);
	}
}
