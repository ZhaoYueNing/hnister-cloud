package cn.zynworld.hnister.bbs.service;

import cn.zynworld.hnister.bbs.domain.BBSPlate;
import cn.zynworld.hnister.bbs.domain.BBSPlateExample;
import cn.zynworld.hnister.bbs.domain.BBSTheme;
import cn.zynworld.hnister.bbs.mappers.BBSPlateMapper;
import cn.zynworld.hnister.bbs.mappers.BBSThemeMapper;
import cn.zynworld.hnister.common.service.BaseAbstractService;
import cn.zynworld.hnister.bbs.convertor.BBSPlateConvertor;
import cn.zynworld.hnister.bbs.dto.BBSPlateDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther Buynow Zhao
 * @create 2018/4/3
 */
@Service
public class BBSPlateService extends BaseAbstractService<BBSPlate,Integer,BBSPlateMapper,BBSPlateExample> {

	@Autowired
	private BBSThemeMapper themeMapper;


	/**
	 * @return 所有的plate并包含了 其theme list
	 */
	public List<BBSPlateDTO> findAllWithTheme() {
		List<BBSPlate> bbsPlates = baseFindByExample(null);
		List<BBSPlateDTO> bbsPlateDTOS = bbsPlates.stream().map(BBSPlateConvertor::do2dto).collect(Collectors.toList());
		List<BBSTheme> bbsThemes = themeMapper.selectByExample(null);
		Map<Integer,List<BBSTheme>> bbsThemeMap = Maps.newHashMap();
		bbsThemes.forEach(theme -> {
			if (theme.getPlateId() == null) {
				return;
			}
			if (!bbsThemeMap.containsKey(theme.getPlateId())) {
				bbsThemeMap.put(theme.getPlateId(), Lists.newArrayList());
			}
			bbsThemeMap.get(theme.getPlateId()).add(theme);
		});
		bbsPlateDTOS.stream().forEach(bbsPlateDTO -> {
			if (bbsThemeMap.containsKey(bbsPlateDTO.getId())) {
				bbsPlateDTO.setThemes(bbsThemeMap.get(bbsPlateDTO.getId()));
			}
		});
		return bbsPlateDTOS;
	}
}
