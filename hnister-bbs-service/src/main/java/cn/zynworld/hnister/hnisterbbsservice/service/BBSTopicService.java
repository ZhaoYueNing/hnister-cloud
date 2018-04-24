package cn.zynworld.hnister.hnisterbbsservice.service;

import cn.zynworld.hnister.common.domain.BBSTheme;
import cn.zynworld.hnister.common.domain.BBSTier;
import cn.zynworld.hnister.common.domain.BBSTopic;
import cn.zynworld.hnister.common.domain.BBSTopicExample;
import cn.zynworld.hnister.common.enums.OrderNumEnum;
import cn.zynworld.hnister.common.enums.bbs.BBSTierStatusEnum;
import cn.zynworld.hnister.common.enums.bbs.BBSTierTypeEnum;
import cn.zynworld.hnister.common.enums.bbs.BBSTopicStautsEnum;
import cn.zynworld.hnister.common.enums.bbs.BBSTopicTypeEnum;
import cn.zynworld.hnister.common.mappers.BBSTierMapper;
import cn.zynworld.hnister.common.mappers.BBSTopicMapper;
import cn.zynworld.hnister.common.service.BaseAbstractService;
import cn.zynworld.hnister.hnisterbbsservice.convertor.BBSTopicConvertor;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSTopicPostDTO;
import cn.zynworld.hnister.hnisterbbsservice.exception.BBSTierException;
import cn.zynworld.hnister.hnisterbbsservice.exception.BBSTopicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/21
 */
@Service
public class BBSTopicService extends BaseAbstractService<BBSTopic,Long,BBSTopicMapper,BBSTopicExample> {

	@Autowired
	private BBSThemeService themeService;
	@Autowired
	private BBSTierService tierService;

	/**
	 * 发布帖子
	 * @param topicPostDTO
	 * @return 返回插入后的topicId 小于等于0 则失败
	 */
	@Transactional
	public Long postTopic(BBSTopicPostDTO topicPostDTO) throws BBSTopicException, BBSTierException {
		BBSTopic topic = BBSTopicConvertor.postDTO2do(topicPostDTO);

		// 获取theme
		BBSTheme theme = themeService.baseFindByPrimaryKey(topic.getThemeId());
		if (theme == null) {
			throw new BBSTopicException("该themeId无效");
		}
		topic.setPlateId(theme.getPlateId());

		// init topic
		topic.setReply(1);
		topic.setMaxTierNum(1);
		topic.setPostTime(new Date());
		if (topic.getOrderNum() == null) {
			topic.setOrderNum(OrderNumEnum.DEFALUT_ORDER.getCode());
		}
		if (topic.getType() == null) {
			topic.setType(BBSTopicTypeEnum.GENERAL_TYPE.getCode());
		}
		if (topic.getStatus() == null) {
			topic.setStatus(BBSTopicStautsEnum.GENERAL_STATUS.getCode());
		}
		// 插入topic
		boolean result = baseAdd(topic) > 0;
		if (!result){
			// 插入失败
			throw new BBSTopicException("插入帖子失败");
		}

		// 第一层的内容
		String firstTierContent = topicPostDTO.getContent();

		// 插入第一层
		BBSTier tier = new BBSTier();
		tier.setContent(firstTierContent);
		tier.setType(BBSTierTypeEnum.GENERAL_TYPE.getCode());
		tier.setStatus(BBSTierStatusEnum.GENERAL_STATUS.getCode());
		tier.setUsername(topicPostDTO.getUsername());
		// 层数
		tier.setNumber(1);
		tier.setReplyNum(0);
		tier.setTopicId(topic.getId());
		tier.setPostTime(topic.getPostTime());
		tier.setThemeId(topic.getThemeId());
		tier.setPlateId(topic.getPlateId());
		result = tierService.baseAdd(tier) > 0;
		if (!result) {
			// 插入首条层失败
			throw new BBSTopicException("插入首层失败");
		}
		return topic.getId();
	}

	@Transactional
	//修改层号或者回复数
	public boolean editTierAndReplyNum(Long id, Integer tierNum,Integer replyNum) {
		BBSTopic topic = new BBSTopic();
		topic.setMaxTierNum(tierNum);
		topic.setReply(replyNum);
		BBSTopicExample example = new BBSTopicExample();
		example.createCriteria().andIdEqualTo(id);
		int i = getMapper().updateByExampleSelective(topic, example);
		return i > 0;
	}

}
