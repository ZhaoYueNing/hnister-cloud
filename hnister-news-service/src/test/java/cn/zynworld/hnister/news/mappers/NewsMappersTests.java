package cn.zynworld.hnister.news.mappers;

import cn.zynworld.hnister.common.domain.News;
import cn.zynworld.hnister.common.domain.NewsExample;
import cn.zynworld.hnister.common.mappers.NewsMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/1/24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsMappersTests {

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void testNewsMapper(){
        List<News> newsList = newsMapper.selectByExample(null);
        System.out.println(newsList);
    }

    @Test
    public void testNull() throws Exception {
        int result = newsMapper.updateModuleIdIsNullByModuleId(18);
        System.out.println(result);
    }

    @Test
    public void testFindByPage() {
        NewsExample newsExample = new NewsExample();
        RowBounds rowBounds = new RowBounds(1,10);
        List<News> news = newsMapper.selectByExampleWithBLOBsWithRowbounds(newsExample, rowBounds);
        System.out.println(news);
    }

    @Test
    public void testNewsFindById() throws Exception {
        News news = newsMapper.selectByPrimaryKey(11L);
        System.out.println(news);
    }
}
