package cn.zynworld.hnister.hnisterbbsservice.rest;

import cn.zynworld.hnister.hnisterbbsservice.domain.BBSPlate;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSPlateDTO;
import cn.zynworld.hnister.hnisterbbsservice.service.BBSPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/4
 */
@RestController
@RequestMapping(path = "rest")
public class BBSPlateRest {
	@Autowired
	private BBSPlateService bbsPlateService;

	@GetMapping(path = "pb/bbsplates")
	public List<BBSPlate> findAll() {
		return bbsPlateService.baseFindByExample(null);
	}

	@GetMapping(path = "pb/bbsplatesWithThemes")
	public List<BBSPlateDTO> findAllWithTheme() {
		return bbsPlateService.findAllWithTheme();
	}

	@PostMapping(path = "pt/bbsplate")
	public ResultBean add(@RequestBody BBSPlate plate) {
		return ResultBean.create(bbsPlateService.baseAdd(plate) > 0);
	}

	@DeleteMapping(path = "pt/bbsplate/{id}")
	public ResultBean deleteById(@PathVariable Integer id) {
		return ResultBean.create(bbsPlateService.baseDeleteByPrimaryKey(id) > 0);
	}

	@PutMapping(path = "pt/bbsplate")
	public ResultBean edit(@RequestBody BBSPlate plate) {
		return ResultBean.create(bbsPlateService.baseAdd(plate) > 0);
	}
}
