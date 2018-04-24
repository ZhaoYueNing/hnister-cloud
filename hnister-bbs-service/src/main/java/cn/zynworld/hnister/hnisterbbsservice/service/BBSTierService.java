package cn.zynworld.hnister.hnisterbbsservice.service;

import cn.zynworld.hnister.common.domain.BBSTier;
import cn.zynworld.hnister.common.domain.BBSTierExample;
import cn.zynworld.hnister.common.domain.BBSTopic;
import cn.zynworld.hnister.common.enums.bbs.BBSTierStatusEnum;
import cn.zynworld.hnister.common.enums.bbs.BBSTierTypeEnum;
import cn.zynworld.hnister.common.mappers.BBSTierMapper;
import cn.zynworld.hnister.common.service.BaseAbstractService;
import cn.zynworld.hnister.hnisterbbsservice.convertor.BBSTierConvertor;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSTierPostDTO;
import cn.zynworld.hnister.hnisterbbsservice.exception.BBSTierException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/22
 */
@Service
public class BBSTierService extends BaseAbstractService<BBSTier,Long,BBSTierMapper,BBSTierExample> {

	@Autowired
	private BBSTopicService topicService;

	@Transactional
	public Long postTierToTopic( BBSTierPostDTO tierPostDTO) throws BBSTierException {
		if (StringUtils.isEmpty(tierPostDTO.getUsername())) {
			throw new BBSTierException("用户异常");
		}
		//获取topic
		BBSTopic topic = topicService.baseFindByPrimaryKey(tierPostDTO.getTopicId());
		if (topic == null) {
			throw new BBSTierException("回复层帖子不存在");
		}
		//为topic 的层数及回复数量加一
		boolean result = topicService.editTierAndReplyNum(topic.getId(),
				topic.getMaxTierNum() + 1, topic.getReply() + 1);
		if (!result) {
			throw new BBSTierException("topic 修改楼层数失败");
		}

		BBSTier tier = BBSTierConvertor.postDTO2DO(tierPostDTO);

		tier.setPlateId(topic.getPlateId());
		tier.setThemeId(topic.getThemeId());
		tier.setNumber(topic.getMaxTierNum() + 1);
		tier.setReplyNum(0);
		tier.setPostTime(new Date());

		if (tier.getStatus() == null) {
			tier.setStatus(BBSTierStatusEnum.GENERAL_STATUS.getCode());
		}

		if (tier.getType() == null) {
			tier.setType(BBSTierTypeEnum.GENERAL_TYPE.getCode());
		}
		result = baseAdd(tier) > 0;
		if (!result) {
			throw new BBSTierException("插入楼层失败");
		}
		return tier.getId();
	}
}
