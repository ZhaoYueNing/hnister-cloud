package cn.zynworld.hnister.news.mappers;

import cn.zynworld.hnister.common.domain.NewsModuleExample;
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
public class NewsModuleMapperTest {
    @Autowired
    private NewsModuleMapper newsModuleMapper;

    @Test
    public void testNumberChange() throws Exception {
        NewsModuleExample newsModuleExample = new NewsModuleExample();
        newsModuleExample.createCriteria().andIdEqualTo(18);
        int result = newsModuleMapper.updateChangeNumberByExample(-1,newsModuleExample);
        System.out.println(result);
    }
}
