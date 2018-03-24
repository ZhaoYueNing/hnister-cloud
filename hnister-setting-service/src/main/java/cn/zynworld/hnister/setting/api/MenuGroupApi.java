package cn.zynworld.hnister.setting.api;

import cn.zynworld.hnister.common.domain.MenuGroup;
import cn.zynworld.hnister.common.mappers.MenuGroupMapper;
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
public class MenuGroupApi {
	@Autowired
	private MenuGroupMapper groupMapper;

	@GetMapping(path = "pb/menuGroups")
	public List<MenuGroup> findAll() {
		return groupMapper.selectByExample(null);
	}
}
