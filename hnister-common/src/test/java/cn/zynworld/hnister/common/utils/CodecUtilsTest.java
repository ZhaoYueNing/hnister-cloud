package cn.zynworld.hnister.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
public class CodecUtilsTest {
    @Test
    public void testSalePassword() throws Exception {
        String sale = CodecUtils.getSale();
        String pw = "abcdefg...";

        String salePassword = CodecUtils.getSalePassword(pw, sale);
        System.out.println(salePassword);
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
        CodecUtils.JwtBean jwtBean = new CodecUtils.JwtBean();

        jwtBean.addHead("typ","jwt");
        jwtBean.addHead("alg","ha256");

        jwtBean.addPlayload("role","admin");
        jwtBean.addPlayload("role","admin");
        System.out.println(jwtBean.getHead("typ"));
        System.out.println(jwtBean);
    }

    @Test
    public void testCreateJwtByJsonToken() throws Exception {
        String jsonToken = "eyJ0eXAiOiJqd3QiLCJhbGciOiJoYTI1NiJ9.eyJyb2xlIjoiYWRtaW4ifQ==.55f08b263402a3100683d9eafe72e2f5d629b7e5e010b51f20a13cdf45dd8c19";
        CodecUtils.JwtBean jwtBean = CodecUtils.JwtBean.getJwtBean(jsonToken);
        System.out.println(jwtBean.getHeadJson());
        System.out.println(jwtBean.getPlayloadJson());
        System.out.println(jwtBean.getHead("typ"));

    }

}