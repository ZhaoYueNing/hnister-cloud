package cn.zynworld.hnister.bbs.convertor;

import cn.zynworld.hnister.bbs.domain.BBSTier;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.bbs.dto.BBSTierPostDTO;
import cn.zynworld.hnister.bbs.vo.BBSTierPostVO;

/**
 * @auther Buynow Zhao
 * @create 2018/4/24
 */
public class BBSTierConvertor {

	public static BBSTier postDTO2DO(BBSTierPostDTO postDTO) {
		return BeanUtils.copyProperties(postDTO, new BBSTier());
	}

	public static BBSTierPostDTO postVO2postDTO(BBSTierPostVO tierPostVO) {
		return BeanUtils.copyProperties(tierPostVO, new BBSTierPostDTO());
	}

}
