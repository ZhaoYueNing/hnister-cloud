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
}
