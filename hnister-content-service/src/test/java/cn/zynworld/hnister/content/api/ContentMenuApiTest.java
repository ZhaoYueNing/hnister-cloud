package cn.zynworld.hnister.content.api;

import cn.zynworld.hnister.common.domain.ContentMenu;
import cn.zynworld.hnister.common.dto.content.ContentMenuDTO;
import cn.zynworld.hnister.common.enums.content.ContentMenuIdMenu;
import cn.zynworld.hnister.common.enums.content.ContentMenuTypeEnum;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhaoyuening on 2018/3/1.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentMenuApiTest {
    @Autowired
    private ContentMenuApi contentMenuApi;



    @Test
    public void testContentMenuAdd() throws Exception {
        ContentMenu contentMenu = new ContentMenu();
        contentMenu.setMenuId(ContentMenuIdMenu.INDEX_NAV.getCode());
        contentMenu.setName("新闻动态");
        contentMenu.setOrderNum(100);
        contentMenu.setUrl("http://www.baidu.com");
        contentMenu.setType(ContentMenuTypeEnum.NO_URL_TYPE.getCode());
        contentMenu.setParentId(0);

        ResultBean result = contentMenuApi.add(contentMenu);

        System.out.println(result);
    }
}
