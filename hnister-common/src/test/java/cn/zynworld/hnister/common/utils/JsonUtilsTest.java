package cn.zynworld.hnister.common.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyuening on 2018/1/27.
 */
public class JsonUtilsTest {

    @Test
    public void testJson2Map() throws Exception {
        CodecUtils.JwtBean jwtBean = new CodecUtils.JwtBean();
        jwtBean.addHead("typ","abc");
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("wp");
        list.add("xp");
        jwtBean.addHead("list",list);
        System.out.println(jwtBean.getHeadJson());
        Map<String, Object> map = JsonUtils.jsonToMap(jwtBean.getHeadJson());
        System.out.println(map.size());
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(new Date().getTime());
    }
}
