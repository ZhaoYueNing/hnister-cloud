package cn.zynworld.hnister.news.rest;

import cn.zynworld.hnister.common.domain.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhaoyuening on 2018/1/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsApiTest {
    @Autowired
    private NewsRest newsApi;

    @Test
    public void testNewsAdd() throws Exception {
        News news = new News();
        news.setAuthor("test author");
        news.setContent("zhaozhao");
        news.setTitle("abc");
        news.setModuleId(null);
    }

}
