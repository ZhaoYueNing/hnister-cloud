package cn.zynworld.hnister.hnisterbbsservice.rest;

import cn.zynworld.hnister.common.domain.BBSTopic;
import cn.zynworld.hnister.common.domain.BBSTopicExample;
import cn.zynworld.hnister.common.utils.AccountUtils;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.hnisterbbsservice.convertor.BBSTopicConvertor;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSTopicPostDTO;
import cn.zynworld.hnister.hnisterbbsservice.service.BBSTopicService;
import cn.zynworld.hnister.hnisterbbsservice.vo.BBSTopicPostVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
	public ResultBean add(@RequestBody BBSTopicPostVO topicPostVO) {
		String username = AccountUtils.getUsername();
		if (StringUtils.isEmpty(username)) {
			return ResultBean.fail("用户认证失败");
		}

		BBSTopicPostDTO topicPostDTO = BBSTopicConvertor.postVO2postDTO(topicPostVO);
		// init post dto
		topicPostDTO.setUsername(username);

		try {
			Long topicId = topicService.postTopic(topicPostDTO);
			if (topicId > 0) {
				return ResultBean.success(topicId);
			}
		} catch (Exception e) {
			return ResultBean.fail(e.getMessage());
		}
		return ResultBean.fail();
	}

	@GetMapping(path = "pb/topics")
	//通过分页查询 id小于等于0查询所有
	public PageBean<BBSTopic> findByPage(@RequestParam Integer themeId,@RequestParam Integer pageCount,@RequestParam Integer pageSize) {
		BBSTopicExample topicExample = null;
		if (themeId > 0) {
			topicExample = new BBSTopicExample();
			topicExample.createCriteria().andThemeIdEqualTo(themeId);
		}
		PageBean<BBSTopic> pageBean = topicService.baseFindByExampleWithPage(topicExample, pageCount, pageSize);
		return pageBean;
	}

	@GetMapping(path = "pb/topic/{id}")
	public BBSTopic findById(@PathVariable Long id) {
		return topicService.baseFindByPrimaryKey(id);
	}


}
