package cn.zynworld.hnister.bbs.convertor;

import cn.zynworld.hnister.bbs.domain.BBSPlate;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.bbs.dto.BBSPlateDTO;

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
