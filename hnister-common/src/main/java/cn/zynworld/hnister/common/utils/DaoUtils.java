package cn.zynworld.hnister.common.utils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @auther Buynow Zhao
 * @create 2018/1/3
 */
public class DaoUtils {
	public static void f(Object obj, Set<String> excepts){
		Field[] fields = obj.getClass().getDeclaredFields();
		String fieldName = null;
		for (Field field :
				fields) {
			fieldName = field.getName();
			if (excepts.contains(fieldName)){
				continue;
			}
			fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		}
	}
}
