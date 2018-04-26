package cn.zynworld.hnister.bbs.service;

import cn.zynworld.hnister.bbs.domain.BBSTheme;
import cn.zynworld.hnister.bbs.domain.BBSThemeExample;
import cn.zynworld.hnister.bbs.mappers.BBSThemeMapper;
import cn.zynworld.hnister.common.service.BaseAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/4
 */
@Service
public class BBSThemeService extends BaseAbstractService<BBSTheme,Integer,BBSThemeMapper,BBSThemeExample> {


	/**
	 * 获取该板块id下的所有主题
	 * @param plateId 板块id
	 * @return 该板块id下的所有主题
	 */
	public List<BBSTheme> findByPlateId(Integer plateId) {
		BBSThemeExample example = createExample();
		example.createCriteria().andPlateIdEqualTo(plateId);
		return getMapper().selectByExample(example);
	}

}
