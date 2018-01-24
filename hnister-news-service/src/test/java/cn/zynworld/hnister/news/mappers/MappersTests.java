package cn.zynworld.hnister.news.mappers;

import cn.zynworld.hnister.common.domain.News;
import cn.zynworld.hnister.common.domain.NewsExample;
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
public class MappersTests {

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void testNewsMapper(){
        List<News> newsList = newsMapper.selectByExample(new NewsExample());
        System.out.println(newsList);

    }

}
