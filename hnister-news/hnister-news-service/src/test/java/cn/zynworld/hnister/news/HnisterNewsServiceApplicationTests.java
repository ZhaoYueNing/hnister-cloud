package cn.zynworld.hnister.news;

import cn.zynworld.hnister.news.domain.NewsExample;
import cn.zynworld.hnister.news.mappers.NewsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HnisterNewsServiceApplicationTests {

	@Autowired
	private NewsMapper newsMapper;

	@Test
	public void contextLoads() {
		newsMapper.selectByExample(new NewsExample());

	}

}
