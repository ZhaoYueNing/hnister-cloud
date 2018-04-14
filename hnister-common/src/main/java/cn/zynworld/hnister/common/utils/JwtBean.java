package cn.zynworld.hnister.common.utils;

import cn.zynworld.hnister.common.enums.account.JwtFieldEnum;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于装载 jwt 信息的载体
 * playload内存储的key {@link cn.zynworld.hnister.common.enums.account.JwtFieldEnum}
 */
public class JwtBean{

    //敏感值 用于对jwt HA256加密
    private final static String SECRET = "jklf-=ertjerk.,sdf";

    private Map<String,Object> headMap = new HashMap<String, Object>();
    private Map<String,Object> playloadMap = new HashMap<String, Object>();


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
        if (jwt == null){
            return null;
        }
        String[] jwts = jwt.split("\\.");
        if (jwts.length != 3){
            return null;
        }
        //验证
        String signature = CodecUtils.getSHA256Str(jwts[0] + "." + jwts[1] + SECRET);
        if (!jwts[2].equals(signature)){
            return null;
        }
        //验证成功 构建jwtBean
        JwtBean jwtBean = null;
        try{
            jwtBean = new JwtBean();
            jwtBean.setHeadMap( JsonUtils.jsonToMap(CodecUtils.decodeBase64(jwts[0])));
            jwtBean.setPlayloadMap( JsonUtils.jsonToMap(CodecUtils.decodeBase64(jwts[1])));

            //判断是否过期
            Object limit = jwtBean.getPlayload(JwtFieldEnum.LIMIT.getField());
            if ((Double)limit < System.currentTimeMillis()) {
                //过期token
                return null;
            }
        }catch (Exception e){
            return null;
        }

        return jwtBean;
    }

    /**
     * 为jwt添加头信息
     */
    public void addHead(String key,Object value){
        headMap.put(key,value);
    }

    /**
     * 为jwt添加荷载
     */
    public void addPlayload(String key,Object value){
        playloadMap.put(key,value);
    }

    public Map<String, Object> getHeadMap() {
        return headMap;
    }

    public Map<String, Object> getPlayloadMap() {
        return playloadMap;
    }

    public Object getHead(String key){
        return headMap.get(key);
    }

    public Object getPlayload(String key){
        return playloadMap.get(key);
    }

    public String getHeadJson(){
        return gson.toJson(headMap);
    }


    public String getPlayloadJson(){
        return gson.toJson(playloadMap);
    }

    public JwtBean setHeadMap(Map<String, Object> headMap) {
        this.headMap = headMap;
        return this;
    }

    public JwtBean setPlayloadMap(Map<String, Object> playloadMap) {
        this.playloadMap = playloadMap;
        return this;
    }

    //将JwtBean对象转换为字符串
    @Override
    public String toString(){
        String jwt = null;
        String headJson = gson.toJson(headMap);
        String playload = gson.toJson(playloadMap);


        //base64
        headJson = CodecUtils.encodeBase64(headJson);
        playload = CodecUtils.encodeBase64(playload);

        jwt = headJson + "." +playload;
        jwt = jwt + "." + CodecUtils.getSHA256Str(jwt + SECRET);
        return  jwt;
    }

}
