package cn.krim.gp.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import cn.krim.gp.core.model.users.User;

public class MyReflectUtils {
	
	private static Map<String, Map<String, Field>> mainMap = new HashMap<String, Map<String,Field>>();
	
	/**
	 * 获取所有访问权限的字段、方法等
	 * 支持多级
	 * @param clazz
	 * @return
	 */
	public static Map<String, Field> getFieldMap(Class<?> clazz) {
		String className = clazz.getName();
		if(!mainMap.containsKey(className)) {
			Map<String, Field> fieldMap = new HashMap<String, Field>();
	        while (clazz != null) {
	        	//当父类为null的时候说明到达了最上层的父类(Object类).
	            for (Field field : clazz.getDeclaredFields()) {
					fieldMap.put(field.getName(), field);
				}
	            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
	        }
			mainMap.put(className, fieldMap);
		}
		return mainMap.get(className);
	}
	
	/**
	 * 更具Class和fieldMap组装对象
	 * @param clazz
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	public static Object getInstaceAndSetFields(Class<?> clazz,Map<Object, Object> fields) throws Exception{
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
	
	/**
	 * 根据对应的fieldName返回set方法
	 * @param name
	 * @return
	 */
	public static String getSetMethodByField(String name) {
		char[] cs=name.toCharArray();
        cs[0]-=32;
        return "set"+String.valueOf(cs);
	}
	
	/**
	 * 根据对应的fieldName返回get方法
	 * @param name
	 * @return
	 */
	public static String getGetMethodByField(String name) {
		char[] cs=name.toCharArray();
        cs[0]-=32;
        return "get"+String.valueOf(cs);
	}
	
	
	public static Object getInstanceWithArray(Class<?> clazz,Map<String, Object[]> entityMap) throws Exception{
		if(entityMap.size()==0) throw new Exception("loss field map");
		Map<Object, Object> fieldsMap = Maps.newHashMap();
		Object[] fieldName = entityMap.get("fieldName");
		Object[] fieldValue = entityMap.get("fieldValue");
		if(fieldName.length!=fieldValue.length) throw new Exception("loss some fields");
		for (int i = 0; i < fieldValue.length; i++) {
			fieldsMap.put(fieldName[i], fieldValue[i]);			
		}
		return getInstaceAndSetFields(clazz, fieldsMap);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		/*Map<Object, Object> map = new HashMap<>();
		map.put("userId", "krim");
		map.put("password", "krim");
		map.put("createTime", Long.valueOf(123032416516461687L));
		Map<String, Field> fieldMap = getFieldMap(User.class);
		Object[] fieldName = {"userId","password","createTime"};
		Object[] fieldValue = {"krim","krim",Long.valueOf(123032416516461687L)};
		for (int i = 0; i < fieldValue.length; i++) {
			if (fieldName[i].equals(fieldMap.get(fieldName[i]).getName())) 
				 System.out.println("find:"+fieldName[i]+" and value:"+fieldValue[i]+" and object type:"+fieldName[i].getClass()+":"+fieldValue[i].getClass());
			
		}*/
		/*Object o =getInstaceAndSetFields(User.class,map);
		if(o instanceof User){
			System.out.println(((User) o).getUserId());
			System.out.println(((User) o).getCreateTime());
		}*/
		Object[] fieldName = {"status","className"};
		Object[] fieldValue = {"1","光信1401"};
		Map<String, Object[]> map = new HashMap<>();
		map.put("fieldName", fieldName);
		map.put("fieldValue", fieldValue);
		String json = JSON.toJSONString(map);
		System.out.println(json);
	}
}
