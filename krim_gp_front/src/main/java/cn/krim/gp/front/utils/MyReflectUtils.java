package cn.krim.gp.front.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;

import cn.krim.gp.front.model.http.ModelFields;

public class MyReflectUtils {
	
private static Map<String, Map<String, Field>> mainMap = Maps.newHashMap();
			
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
					fieldMap.put(field.getName().toLowerCase(), field);
				}
	            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
	        }
			mainMap.put(className, fieldMap);
		}
		return mainMap.get(className);
	}
	/**
	 * 获取类的实例，并按照属性map设置值
	 * @param clazz
	 * @param fields
	 * @return
	 * @throws Exception
	 */
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
	public static String getSetMethodByField(String name) {
		char[] cs=name.toCharArray();
        cs[0]-=32;
        return "set"+String.valueOf(cs);
	}
	public static String getGetMethodByField(String name) {
		char[] cs=name.toCharArray();
        cs[0]-=32;
        return "get"+String.valueOf(cs);
	}
	
	public static Map<Object,Object> fieldsMap(String json) throws Exception{
		JSONArray fn = JSON.parseObject(json).getJSONArray("fieldName");
		JSONArray fv = JSON.parseObject(json).getJSONArray("fieldValue");
		Object[] fieldName =  fn.toArray();
		Object[] fieldValue = fv.toArray();
			if(fieldName.length!=fieldValue.length){
				throw new Exception("查询数据中出现了丢失字段的现象，请确认没有必填项为空");
			}
			Map<Object, Object> fildsMap = Maps.newHashMap();
			for (int i = 0; i < fieldName.length; i++) {
				fildsMap.put(fieldName[i], fieldValue[i]);
			}
		return fildsMap;
	}
	
	public static Map<String, Object[]> map2Array(String json) throws Exception{
		JSONArray fn = JSON.parseObject(json).getJSONArray("fieldName");
		JSONArray fv = JSON.parseObject(json).getJSONArray("fieldValue");
		Object[] fieldName =  fn.toArray();
		Object[] fieldValue = fv.toArray();
		if(fieldName.length!=fieldValue.length||fieldName.length==0){
			throw new Exception("查询数据中出现了丢失字段的现象，请确认没有必填项为空");
		}
		Map<String, Object[]> map2Array = Maps.newHashMap();
		map2Array.put("fieldName", fieldName);
		map2Array.put("fieldValue", fieldValue);
		return map2Array;
	}
	
}
