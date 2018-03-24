package cn.zynworld.hnister.common.utils;

import com.google.gson.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhaoyuening on 2018/1/26.
 * 密码盐值加密
 */
public class CodecUtils {


    private static final Base64 BASE_64 = new Base64();
    /**
     * 盐值加密
     * @param password
     * @param sale
     * @return
     */
    public static String getSalePassword(String password,String sale){
        password = getSHA256Str(password);
        password = password + sale;
        password = getSHA256Str(password);
        return password;
    }

    /**
     * 编码base64
     * @param str
     * @return
     */
    public static String encodeBase64(String str){
        return BASE_64.encodeToString(str.getBytes());
    }

    /**
     * 解码base64
     * @param str
     * @return
     */
    public static String decodeBase64(String str){
        return new String(BASE_64.decode(str.getBytes()));
    }

    /**
     * 获取盐值
     * @return
     */
    public  static String getSale(){
        return UUID.randomUUID().toString();
    }

    /**
     * 获取SHA256加密字符串
     * @param str
     * @return
     */
    public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    /**
     * 检验用户登录
     * @param password
     * @param sale
     * @param encodedPassword
     * @return
     */
    public static boolean checkUser(String password,String sale,String encodedPassword){
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(sale) || StringUtils.isEmpty(encodedPassword)){
            return false;
        }

        String salePassword = getSalePassword(password, sale);
        if (salePassword.equals(encodedPassword)){
            return true;
        }
        return false;
    }





}

