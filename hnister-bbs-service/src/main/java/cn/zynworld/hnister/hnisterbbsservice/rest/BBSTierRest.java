package cn.zynworld.hnister.hnisterbbsservice.rest;

import cn.zynworld.hnister.common.domain.BBSTier;
import cn.zynworld.hnister.common.domain.BBSTierExample;
import cn.zynworld.hnister.common.mappers.BBSTierMapper;
import cn.zynworld.hnister.common.utils.AccountUtils;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.hnisterbbsservice.convertor.BBSTierConvertor;
import cn.zynworld.hnister.hnisterbbsservice.dto.BBSTierPostDTO;
import cn.zynworld.hnister.hnisterbbsservice.exception.BBSTierException;
import cn.zynworld.hnister.hnisterbbsservice.service.BBSTierService;
import cn.zynworld.hnister.hnisterbbsservice.vo.BBSTierPostVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther Buynow Zhao
 * @create 2018/4/23
 */
@RestController
@RequestMapping(path = "rest")
public class BBSTierRest {

	@Autowired
	private BBSTierService tierService;

	//用户发布帖子接口
	@PostMapping(path = "pt/tier")
	public ResultBean add(@RequestBody BBSTierPostVO tierPostVO) {
		String username = AccountUtils.getUsername();
		if (StringUtils.isEmpty(username)) {
			return ResultBean.fail("请求失败，用户鉴定失败");
		}

		BBSTierPostDTO tierPostDTO = BBSTierConvertor.postVO2postDTO(tierPostVO);
		// init tierPostDTO
		tierPostDTO.setUsername(username);

		try {
			Long tierId = tierService.postTierToTopic(tierPostDTO);
			if (tierId > 0) {
				return ResultBean.success(tierId);
			}

		} catch (BBSTierException e) {}
		return ResultBean.fail();
	}

	/**
	 *
	 * @param topicId
	 * @param pageCount
	 * @param pageSize
	 * @param username 查询该用户在该主题下的所有帖子，非必须
	 * @return pageBean
	 */
	@GetMapping(path = "pb/tiers")
	public PageBean<BBSTier> findByPage(@RequestParam Long topicId,@RequestParam Integer pageCount,
										@RequestParam Integer pageSize,@RequestParam(required = false) String username) {
		BBSTierExample example = new BBSTierExample();
		example.createCriteria().andTopicIdEqualTo(topicId);
		if (!StringUtils.isEmpty(username)) {
			example.createCriteria().andUsernameEqualTo(username);
		}
		return tierService.baseFindByExampleWithPageWithBLOBs(example,pageCount,pageSize);
	}
}
