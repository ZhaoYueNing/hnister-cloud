package cn.zynworld.hnister.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.*;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
public class JsonUtils {
    private final static Gson GSON = new Gson();

    public static Map<String,Object> jsonToMap(String json){
        return GSON.fromJson(json, Map.class);
    }


    @Deprecated
    public static Object jsonToObject(JsonElement element){
        if (element.isJsonPrimitive()){
            return element.getAsJsonPrimitive().getAsString();
        }
        if (element.isJsonObject()){
            Map<String, Object> map = new HashMap<String, Object>();
            for (String key :
                    element.getAsJsonObject().keySet()) {
                JsonElement jsonElement = element.getAsJsonObject().get(key);
                map.put(key, jsonToObject(jsonElement));
            }
            return map;
        }
        if (element.isJsonArray()){
            List<Object> list = new ArrayList<Object>();
            for (int i=0;i<element.getAsJsonArray().size();i++){
                JsonElement jsonElement = element.getAsJsonArray().get(i);
                list.add(jsonToObject(jsonElement));
            }
            return list;
        }
        return null;
    }
}
