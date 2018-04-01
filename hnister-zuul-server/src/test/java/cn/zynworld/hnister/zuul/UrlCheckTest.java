package cn.zynworld.hnister.zuul;

import org.junit.Test;

/**
 * Created by zhaoyuening on 2018/3/13.
 */
public class UrlCheckTest {
//    @Test
    public void test() throws Exception {
        String url = "/hnister-security-service/rest/pt/roles";
        String[] split = url.split("/");
        if (split[2].equals("rest")) {
            System.out.println(split[3]);
        }
    }
}
