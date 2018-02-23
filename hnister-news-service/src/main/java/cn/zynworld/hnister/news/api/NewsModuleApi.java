package cn.zynworld.hnister.news.api;

import cn.zynworld.hnister.common.domain.NewsModule;
import cn.zynworld.hnister.common.domain.NewsModuleExample;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.common.mappers.NewsMapper;
import cn.zynworld.hnister.common.mappers.NewsModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/1/6
 */
@RestController
public class NewsModuleApi {

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private NewsModuleMapper newsModuleMapper;

	@GetMapping(path = "newsModules")
	public List<NewsModule> findAll() {
		return newsModuleMapper.selectByExample(null);
	}

	@Transactional
	@RequestMapping(path = "newsModule",method = RequestMethod.POST)
	public ResultBean add(@RequestBody NewsModule newsModule){
		//初始模块文章数量0
		newsModule.setNumber(0L);
		int newsModuleId = newsModuleMapper.insert(newsModule);
		return ResultBean.create(newsModuleId > 0);
	}

	@Transactional
	@RequestMapping(path = "newsModule/{id}",method = RequestMethod.DELETE)
	public ResultBean deleteById(@PathVariable  int id){
		//将该模块下所有所有文章module id 为null
		newsMapper.updateModuleIdIsNullByModuleId(id);
		//remove module
		int result = newsModuleMapper.deleteByPrimaryKey(id);

		return ResultBean.create(result > 0);
	}


	@RequestMapping(path = "newsModule",method = RequestMethod.PUT)
	public ResultBean edit(@RequestBody NewsModule newsModule){
		if (newsModule.getId() == null) {
			return ResultBean.fail("id 不得为 null");
		}
		int result = newsModuleMapper.updateByPrimaryKey(newsModule);
		return ResultBean.create(result > 0);
	}

}
