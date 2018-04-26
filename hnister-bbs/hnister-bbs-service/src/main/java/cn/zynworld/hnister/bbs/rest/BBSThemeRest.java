package cn.zynworld.hnister.bbs.rest;

import cn.zynworld.hnister.bbs.domain.BBSTheme;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.bbs.service.BBSThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/4
 */
@RestController
@RequestMapping(path = "rest")
public class BBSThemeRest {
	@Autowired
	private BBSThemeService themeService;

	@PostMapping(path = "pt/theme")
	public ResultBean add(BBSTheme theme) {
		return ResultBean.create(themeService.baseAdd(theme) > 0);
	}

	@DeleteMapping(path = "pt/theme/{id}")
	public ResultBean deleteById(@PathVariable Integer id) {
		return ResultBean.create(themeService.baseDeleteByPrimaryKey(id) > 0);
	}

	@PutMapping(path = "pt/theme")
	public ResultBean edit(@RequestBody BBSTheme theme) {
		return ResultBean.create(themeService.baseEdit(theme) > 0);
	}

	@GetMapping(path = "pb/themes")
	public List<BBSTheme> findAll() {
		return themeService.baseFindByExample(null);
	}

	@GetMapping(path = "pb/theme/{themeId}")
	public BBSTheme findById(@PathVariable Integer themeId) {
		return themeService.baseFindByPrimaryKey(themeId);
	}

	@GetMapping(path = "pb/themes/plateId/{plateId}")
	public List<BBSTheme> findByPlateId(Integer plateId) {
		return themeService.findByPlateId(plateId);
	}

}
