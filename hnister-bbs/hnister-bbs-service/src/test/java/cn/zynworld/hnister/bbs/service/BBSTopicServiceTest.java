package cn.zynworld.hnister.bbs.service;

import cn.zynworld.hnister.bbs.domain.BBSTopic;
import cn.zynworld.hnister.common.enums.bbs.BBSTopicStautsEnum;
import cn.zynworld.hnister.common.enums.bbs.BBSTopicTypeEnum;
import cn.zynworld.hnister.bbs.mappers.BBSTopicMapper;
import cn.zynworld.hnister.bbs.dto.BBSTopicPostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BBSTopicServiceTest {

	@Autowired
	private BBSTopicMapper bbsTopicMapper;
	@Autowired
	private BBSTopicService topicService;


	@Test
	public void testInsert() throws Exception {
		BBSTopic topic = new BBSTopic();
		topic.setUsername("buynow");
		topic.setReply(1);
		topic.setTitle("demo");
		topic.setType(BBSTopicTypeEnum.GENERAL_TYPE.getCode());
		topic.setStatus(BBSTopicStautsEnum.GENERAL_STATUS.getCode());
		topic.setPlateId(5);
		topic.setThemeId(1);
		topic.setPostTime(new Date());
		boolean success = bbsTopicMapper.insert(topic) > 0;
		Long id = topic.getId();
		System.out.println(id);
	}

	@Test
	public void testPostTopic() throws Exception {
		BBSTopicPostDTO topicPostDTO = new BBSTopicPostDTO();
		topicPostDTO.setUsername("buynow");
		topicPostDTO.setStatus(BBSTopicStautsEnum.GENERAL_STATUS.getCode());
		topicPostDTO.setType(BBSTopicStautsEnum.GENERAL_STATUS.getCode());
		topicPostDTO.setThemeId(1);
		topicPostDTO.setTitle("demo");
		topicPostDTO.setContent("demo content");

		topicService.postTopic(topicPostDTO);
	}

}
