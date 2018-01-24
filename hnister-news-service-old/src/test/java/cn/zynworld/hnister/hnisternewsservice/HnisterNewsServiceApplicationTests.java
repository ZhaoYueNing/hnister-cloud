package cn.zynworld.hnister.hnisternewsservice;

import cn.zynworld.hnister.hnisternewsservice.dao.NewsDao;
import cn.zynworld.hnister.common.domain.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import cn.zynworld.hnister.common.utils.PageBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HnisterNewsServiceApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NewsDao newsDao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testNewsPageQuery() {
		PageBean<News> pageBean = newsDao.findByPage(1, 10, 17);
//		List<News> pageBean = newsDao.findAll();
		System.out.println();
	}

}
