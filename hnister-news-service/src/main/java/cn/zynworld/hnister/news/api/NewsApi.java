package cn.zynworld.hnister.news.api;

import cn.zynworld.hnister.common.domain.News;
import cn.zynworld.hnister.common.domain.NewsExample;
import cn.zynworld.hnister.common.domain.NewsModuleExample;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.common.mappers.NewsMapper;
import cn.zynworld.hnister.common.mappers.NewsModuleMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * 批量查询将无法查询出text类型的content 保证了传输速率
 * @auther Buynow Zhao
 * @create 2018/1/2
 */
@RestController
public class NewsApi {


	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsModuleMapper newsModuleMapper;


	@GetMapping(path = "news/{id}")
	public News findById(@PathVariable Long id){
		News news = newsMapper.selectByPrimaryKey(id);
		return news;
	}

	/**
	 *
	 * @param news 需要新增的News
	 * @return 成功返回 success resultBean & msg = newsId
	 */
	@Transactional
	@RequestMapping(path = "/news",method = RequestMethod.POST)
	public ResultBean addNews(@RequestBody News news){
		news.setPostDate(new Date());
		int newsId = newsMapper.insert(news);

		if (newsId > 0 ){
			NewsModuleExample newsModuleExample = new NewsModuleExample();
			newsModuleExample.createCriteria().andIdEqualTo(news.getModuleId());
			newsModuleMapper.updateChangeNumberByExample(1,newsModuleExample);
		}
		return ResultBean.create(newsId > 0).setMsg(newsId);
	}


	/**
	 * 查询所有news，非分页
	 */
	@GetMapping(path = "news")
	public List<News> findAll() {
		List<News> list = newsMapper.selectByExample(null);
		return list;
	}

	/**
	 *
	 * @param pageCount 页数
	 * @param pageSize 分页大小
	 * @param moduleId -1 查询所有 0 查询草稿箱 >0 查询对应id的文章
	 * @return pageBean
	 * http://localhost:10000/hnister-news-service/news?page=true&pageCount=1&pageSize=2&moduleId=3
	 */
	@GetMapping(path = "news/@/for=page")
	public PageBean<News> findByPage(@PathParam("pageCount") Integer pageCount, @PathParam("pageSize") Integer pageSize, @PathParam("moduleId") Integer moduleId){
		NewsExample newsExample = null;
		PageBean<News> pageBean = new PageBean<News>();
		pageBean.setPageCount(pageCount).setPageSize(pageSize);

		RowBounds rowBounds = new RowBounds(pageBean.getFirstItemIndex(),pageBean.getPageSize());
		//判断模块类型
		if (moduleId > 0) {
			newsExample = new NewsExample();
			newsExample.createCriteria().andModuleIdEqualTo(moduleId);
		} else if (moduleId == 0){
			//moduleId = 0 为查询草稿箱内的文章 数据库内module_id 为null
			newsExample = new NewsExample();
			newsExample.createCriteria().andModuleIdIsNull();
		}
		//分页查询
		List<News> newsList = newsMapper.selectByExampleWithRowbounds(newsExample, rowBounds);
		//获取该查询非分页情况总数
		int total = newsMapper.countByExample(newsExample);
		pageBean.setTotal((long) total);
		pageBean.setItems(newsList);

		return pageBean;
	}

	@Transactional
	@DeleteMapping(path = "news/{id}")
	public ResultBean deleteById(@PathVariable Long id){
		News news = newsMapper.selectByPrimaryKey(id);
		if (news == null){
			return ResultBean.fail("无该 news");
		}
		int result = newsMapper.deleteByPrimaryKey(id);
		if (result > 0 && news.getModuleId() != null && news.getModuleId() != 0){
//			//删除成功 将该文章的module 文章数量减一
			NewsModuleExample newsModuleExample = new NewsModuleExample();
			newsModuleExample.createCriteria().andIdEqualTo(news.getModuleId());
			newsModuleMapper.updateChangeNumberByExample(-1,newsModuleExample);
		}
		return ResultBean.create(result > 0);
	}

	@Transactional
	@PutMapping(path = "news")
	public ResultBean update(@RequestBody News news) {
		if (news.getId() == null) {
			return ResultBean.fail("id 不得为空");
		}
		if (news.getModuleId() == 0){
			news.setModuleId(null);
		}
		int result = newsMapper.updateByPrimaryKey(news);
		return ResultBean.create(result > 0);
	}

}
