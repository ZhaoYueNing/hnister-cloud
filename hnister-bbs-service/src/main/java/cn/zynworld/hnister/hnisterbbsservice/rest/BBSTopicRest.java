package cn.zynworld.hnister.hnisterbbsservice.rest;

import cn.zynworld.hnister.common.domain.BBSTopic;
import cn.zynworld.hnister.common.utils.AccountUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.hnisterbbsservice.service.BBSTopicService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @auther Buynow Zhao
 * @create 2018/4/21
 */
@RestController
@RequestMapping(path = "rest")
public class BBSTopicRest {

	@Autowired
	private BBSTopicService topicService;

	//用户发布帖子
	@PostMapping("pt/topic")
	public ResultBean add(@RequestBody BBSTopic topic) {
		String username = AccountUtils.getUsername();
		if (StringUtils.isEmpty(username)) {
			return ResultBean.fail("身份异常，发布失败");
		}
		// 设定初始化值
		topic.setType(1);
		topic.setPostTime(new Date());
		topic.setUsername(username);
		topic.setReply(1);
		return ResultBean.create(topicService.baseAdd(topic) > 0);
	}
}
