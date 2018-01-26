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

/**
 * Created by zhaoyuening on 2018/1/26.
 * 密码盐值加密
 */
public class CodecUtils {
    //敏感值 用于对jwt HA256加密
    private final static String SECRET = "jklf-=ertjerk.,sdf";

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
        return new Date().getTime()+"";
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

    //JWT
    public static String getJwtString(){
        return null;
    }
    //用于装载 jwt 信息的载体
    public static class JwtBean{
        private Map<String,String> headMap = new HashMap<String, String>();
        private Map<String,String> playloadMap = new HashMap<String, String>();
        private static Gson gson = new GsonBuilder().create();
        private static JsonParser jsonParser = new JsonParser();


        public JwtBean(){}

        /**
         * 通过jwt串 解码后获取JwtBean
         * 若非合法jwt 将返回null
         * @param jwt
         * @return
         */
        public static JwtBean getJwtBean(String jwt){
            String[] jwts = jwt.split("\\.");
            if (jwts.length != 3){
                return null;
            }
            //验证
            String signature = getSHA256Str(jwts[0] + "." + jwts[1] + SECRET);
            if (!jwts[2].equals(signature)){
                return null;
            }
            //验证成功 构建jwtBean
            JwtBean jwtBean = null;
            try{
                JsonObject headJsonObject = jsonParser.parse(decodeBase64(jwts[0])).getAsJsonObject();
                JsonObject playloadJsonObject = jsonParser.parse(decodeBase64(jwts[1])).getAsJsonObject();

                jwtBean = new JwtBean();

                for (String key :
                        headJsonObject.keySet()) {
                    jwtBean.getHeadMap().put(key, headJsonObject.get(key).getAsString());
                }
                for (String key :
                        playloadJsonObject.keySet()) {
                    jwtBean.getPlayloadMap().put(key, playloadJsonObject.get(key).getAsString());
                }
            }catch (Exception e){
                return null;
            }

            return jwtBean;
        }

        public void addHead(String key,String value){
            headMap.put(key,value);
        }

        public void addPlayload(String key,String value){
            playloadMap.put(key,value);
        }

        public Map<String, String> getHeadMap() {
            return headMap;
        }

        public Map<String, String> getPlayloadMap() {
            return playloadMap;
        }

        public String getHead(String key){
            return headMap.get(key);
        }

        public String getPlayload(String key){
            return playloadMap.get(key);
        }

        public String getHeadJson(){
            return gson.toJson(headMap);
        }


        public String getPlayloadJson(){
            return gson.toJson(playloadMap);
        }

        @Override
        public String toString(){
            String jwt = null;
            String headJson = gson.toJson(headMap);
            String playload = gson.toJson(playloadMap);


            //base64
            headJson = encodeBase64(headJson);
            playload = encodeBase64(playload);

            jwt = headJson + "." +playload;
            jwt = jwt + "." + getSHA256Str(jwt + SECRET);
            return  jwt;
        }

    }

}

