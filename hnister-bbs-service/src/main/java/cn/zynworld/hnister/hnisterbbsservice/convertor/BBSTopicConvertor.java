package cn.zynworld.hnister.hnisterbbsservice.convertor;

import cn.zynworld.hnister.common.domain.BBSTopic;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSTopicPostDTO;
import cn.zynworld.hnister.hnisterbbsservice.vo.BBSTopicPostVO;

/**
 * @auther Buynow Zhao
 * @create 2018/4/23
 */
public class BBSTopicConvertor {
	public static BBSTopic postDTO2do(BBSTopicPostDTO topicPostDTO) {
		return BeanUtils.copyProperties(topicPostDTO, new BBSTopic());
	}

	public static BBSTopicPostDTO postVO2postDTO(BBSTopicPostVO topicPostVO) {
		return BeanUtils.copyProperties(topicPostVO, new BBSTopicPostDTO());
	}
}
