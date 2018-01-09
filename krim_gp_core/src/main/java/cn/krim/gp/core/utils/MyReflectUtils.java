package cn.krim.gp.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.krim.gp.core.modle.users.User;

public class MyReflectUtils {
	private static Map<String, Map<String, Field>> mainMap = new HashMap<String, Map<String,Field>>();
	public static Object getInstaceAndSetFields(Class<?> clazz,Map<String, Object> fields) throws Exception{
		//获取实例对象
		Object o = clazz.newInstance();
		//获取字段列表
		while (clazz != null) {
        	//当父类为null的时候说明到达了最上层的父类(Object类).
            for (Field field : clazz.getDeclaredFields()) {
            	//获取field类型
            	String fieldType = field.getGenericType().toString();
               	String setmethodName = getSetMethodByField(field.getName());
               	Method m = null;
            	if("class java.lang.String".equals(fieldType)){
                	m = clazz.getDeclaredMethod(setmethodName, String.class);
            	}else if("class java.lang.Integer".equals(fieldType)){
            		m = clazz.getDeclaredMethod(setmethodName, Integer.class);                	
            	}else if("class java.lang.Double".equals(fieldType)){
            		m = clazz.getDeclaredMethod(setmethodName, Double.class);
            	}else if("class java.lang.Long".equals(fieldType)){
            		m = clazz.getDeclaredMethod(setmethodName, Long.class);
            	}
            	//调用set方法
            	m.invoke(o, fields.get(field.getName()));
			}
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }
		return o;		
	}
	private static String getSetMethodByField(String name) {
		char[] cs=name.toCharArray();
        cs[0]-=32;
        return "set"+String.valueOf(cs);
	}
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", "krim");
		map.put("password", "12345");
		map.put("createTime", Long.valueOf(123032416516461687L));
		Object o =getInstaceAndSetFields(User.class,map);
		if(o instanceof User){
			System.out.println(((User) o).getUserId());
			System.out.println(((User) o).getCreateTime());
		}
	}
}
