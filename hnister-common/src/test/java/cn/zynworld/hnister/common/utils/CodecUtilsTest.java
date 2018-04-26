package cn.zynworld.hnister.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
public class CodecUtilsTest {
    @Test
    public void testSaltPassword() throws Exception {
        String salt = CodecUtils.getSalt();
        String pw = "abcdefg...";

        String saltPassword = CodecUtils.getSaltPassword(pw, salt);
        System.out.println(saltPassword);
    }


    @Test
    public void testBASE64() throws Exception {
        String text = "abcd";
        Base64 base64 = new Base64();
        String s1 = base64.encodeToString(text.getBytes());
        byte[] s2 = base64.decode(s1.getBytes());
        System.out.println(new String(s2));
    }

    @Test
    public void testBase64() throws Exception {
        String text = "abcd";
        String s1 = CodecUtils.encodeBase64(text);
        String s2 = CodecUtils.decodeBase64(s1);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void testJwt() throws Exception {
        JwtBean jwtBean = new JwtBean();

        jwtBean.addHead("typ","jwt");
        jwtBean.addHead("alg","ha256");

        jwtBean.addPlayload("role","admin");
        jwtBean.addPlayload("role","admin");
        System.out.println(jwtBean.getHead("typ"));
        System.out.println(jwtBean);
    }

//    @Test
    public void testCreateJwtByJsonToken() throws Exception {
        String jsonToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIQTI1NiJ9.eyJyb2xlIjpbeyJyb2xlSWQiOjEsInVzZXJuYW1lIjoiemhhb3poYW96aGFvMiJ9XX0=.26d6c858e666ee510eb14640689e1bb121c029240678f4d37277141bc699d933";
        JwtBean jwtBean = JwtBean.getJwtBean(jsonToken);
        System.out.println(jwtBean.getHeadJson());
        System.out.println(jwtBean.getPlayloadJson());
    }

    @Test
    public void testCreateJwtByJsonToken2() throws Exception {
        JwtBean jwtBean = new JwtBean();
        jwtBean.addHead("typ","jwt");
        jwtBean.addHead("alg","ha256");

        jwtBean.addPlayload("role",new Integer[]{1,2});

        JwtBean createdJwtBean = JwtBean.getJwtBean(jwtBean.toString());
        System.out.println(createdJwtBean);
    }
}
