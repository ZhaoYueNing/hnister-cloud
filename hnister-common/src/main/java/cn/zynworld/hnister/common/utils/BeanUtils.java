package cn.zynworld.hnister.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyuening on 2018/2/10.
 */
public class BeanUtils {

    /**
     * 通过反射将 sourceObj相同名的属性赋予targetObj
     * @param sourceObj 被拷贝属性对象
     * @param targetObj 被赋值属性对象
     */
    public static void copyProperties(Object sourceObj,Object targetObj){
        final String GET = "get";
        final String SET = "set";

        if (sourceObj == null || targetObj == null) {
            return;
        }
        Method[] sourceObjMethods = sourceObj.getClass().getMethods();
        Class<?> targetObjClass = targetObj.getClass();

        //遍历sourceObj 所有get开头方法
        for (Method method :
                sourceObjMethods) {
            try {
                //排除非get开头 及getClass方法
                if (!method.getName().substring(0,3).equals(GET) || method.getName().equals("getClass")){
                    continue;
                }
                //targetObj 是否有对应set方法
                String setMethodName = SET + method.getName().substring(3);
                Method targetSetMethod = null;
                try{
                    targetSetMethod = targetObjClass.getMethod(setMethodName, method.getReturnType());
                }catch (NoSuchMethodException e){
                }
                if (targetSetMethod == null) {
                    continue;
                }
                //target 存在该方法
                //获取get返回值
                Object getValue = method.invoke(sourceObj);
                //赋值给targetObj
                targetSetMethod.invoke(targetObj,getValue);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
