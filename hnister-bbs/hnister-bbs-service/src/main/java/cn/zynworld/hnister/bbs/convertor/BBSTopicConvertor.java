package cn.zynworld.hnister.bbs.convertor;

import cn.zynworld.hnister.bbs.domain.BBSTopic;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.bbs.dto.BBSTopicPostDTO;
import cn.zynworld.hnister.bbs.vo.BBSTopicPostVO;

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
