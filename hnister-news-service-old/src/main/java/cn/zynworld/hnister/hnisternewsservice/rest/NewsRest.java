package cn.zynworld.hnister.hnisternewsservice.rest;

import cn.zynworld.hnister.hnisternewsservice.dao.NewsDao;
import cn.zynworld.hnister.hnisternewsservice.dao.NewsModuleDao;
import cn.zynworld.hnister.common.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/1/2
 */
@RestController
@Transactional
public class NewsRest {
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private NewsModuleDao newsModuleDao;


	@RequestMapping(path = "/news",method = RequestMethod.POST)
	public ResultBean addNews(@RequestBody News news){
		news.setPostDate(new Date());
//		为该模块文章数量加一
		if (news.getModuleId() != null){
			newsModuleDao.editNumber(news.getModuleId(),1);
		}
		return ResultBean.create(newsDao.add(news));
	}

	@GetMapping(path = "news",params = "!page")
	public List<News> findAll() {
		List<News> list = newsDao.findAll();
		return list;
	}

//	分页查询功能
//	当前页、页大小、条目总数

	/**
	 *
	 * @param pageCount 页数
	 * @param pageSize 分页大小
	 * @param moduleId -1 查询所有 0 查询草稿箱 >0 查询对应id的文章
	 * @return pageBean
	 * http://localhost:10000/hnist-news-service/news?page=true&pageCount=1&pageSize=2&moduleId=3
	 */
	@GetMapping(path = "news",params = "page=true")
	public PageBean<News> findByPage(@PathParam("pageCount") Integer pageCount, @PathParam("pageSize") Integer pageSize, @PathParam("moduleId") Integer moduleId){
		PageBean<News> pageBean = newsDao.findByPage(pageCount, pageSize, moduleId);
		return pageBean;
	}


	@DeleteMapping(path = "news/{id}")
	public ResultBean deleteById(@PathVariable Long id){
		boolean result = newsDao.deleteById(id);
		return ResultBean.create(result);
	}


	@PutMapping(path = "news")
	public ResultBean update(@RequestBody News news) {
		return ResultBean.create(newsDao.update(news));
	}
}
