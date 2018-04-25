package cn.zynworld.hnister.hnisterbbsservice.convertor;

import cn.zynworld.hnister.hnisterbbsservice.domain.BBSPlate;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSPlateDTO;

/**
 * @auther Buynow Zhao
 * @create 2018/4/4
 */
public class BBSPlateConvertor {

	public static BBSPlateDTO do2dto(BBSPlate bbsPlate) {
		BBSPlateDTO bbsPlateDTO = BBSPlateDTO.builder().build();
		BeanUtils.copyProperties(bbsPlate,bbsPlateDTO);
		return bbsPlateDTO;
	}
}
