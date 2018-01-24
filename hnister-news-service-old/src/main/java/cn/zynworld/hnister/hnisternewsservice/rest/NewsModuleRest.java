package cn.zynworld.hnister.hnisternewsservice.rest;

import cn.zynworld.hnister.hnisternewsservice.dao.NewsDao;
import cn.zynworld.hnister.hnisternewsservice.dao.NewsModuleDao;
import cn.zynworld.hnister.common.domain.NewsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import cn.zynworld.hnister.common.utils.ResultBean;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/1/6
 */
@RestController
@Transactional
public class NewsModuleRest {
	@Autowired
	private NewsModuleDao newsModuleDao;
	@Autowired
	private NewsDao newsDao;

	@GetMapping(path = "newsModules")
	public List<NewsModule> findAll() {
		return newsModuleDao.findAll();
	}

	@RequestMapping(path = "newsModule",method = RequestMethod.POST)
	public ResultBean add(@RequestBody NewsModule newsModule){
		return ResultBean.create(newsModuleDao.add(newsModule));
	}


	@RequestMapping(path = "newsModule/{id}",method = RequestMethod.DELETE)
	public ResultBean deleteById(@PathVariable  int id){
//		将该模块下所有所有文章module id 为null
		newsDao.updateByModuleIdSetNull(id);
		return ResultBean.create(newsModuleDao.deleteById(id));
	}

	@RequestMapping(path = "newsModule",method = RequestMethod.PUT)
	public ResultBean edit(@RequestBody NewsModule newsModule){
		return ResultBean.create(newsModuleDao.edit(newsModule));
	}

}
