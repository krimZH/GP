package cn.krim.gp.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	 * 根据Class和fieldMap组装对象
	 * @param clazz
	 * @param fieldsMap
	 * @return
	 * @throws Exception
	 */
	public static Object getInstaceAndSetFields(Class<?> clazz,Map<Object, Object> fieldsMap) throws Exception{
		//获取实例对象
		Object o = clazz.newInstance();
		//获取字段列表
		while (clazz != null) {
        	//当父类为null的时候说明到达了最上层的父类(Object类).
            for (Field field : clazz.getDeclaredFields()) {
            	//校验参数类型和方法
            	fieldsMap = getMethodAndCheckValid(field,clazz ,fieldsMap);
            	//获取set方法
            	Method setmethod = (Method) fieldsMap.get("setMethod");
            	//调用set方法
               	setmethod.invoke(o, fieldsMap.get(field.getName()));
			}
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }
		return o;		
	}
	
	/**
	 * 根据对应的fieldName返回set方法
	 * 检查value和field的类型一致性
	 * @param name
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static Map<Object,Object> getMethodAndCheckValid(Field field,Class<?> clazz,Map<Object, Object> fields) throws NoSuchMethodException, SecurityException {
		char[] cs=field.getName().toCharArray();
        cs[0]-=32;
        Method m = null;
        String fieldType = field.getGenericType().toString();
        if("class java.lang.String".equals(fieldType)){
        	m = clazz.getDeclaredMethod("set"+String.valueOf(cs), String.class);
    	}else if("class java.lang.Integer".equals(fieldType)){
    		m = clazz.getDeclaredMethod("set"+String.valueOf(cs), Integer.class);
    		if(fields.get(field.getName()) instanceof String){
    			fields.put(field.getName(), Integer.parseInt((String) fields.get(field.getName())));
    		}
    	}else if("class java.lang.Double".equals(fieldType)){
    		m = clazz.getDeclaredMethod("set"+String.valueOf(cs), Double.class);
    		if(fields.get(field.getName()) instanceof String){
    			fields.put(field.getName(), Double.parseDouble((String) fields.get(field.getName())));
    		}
    	}else if("class java.lang.Long".equals(fieldType)){
    		m = clazz.getDeclaredMethod("set"+String.valueOf(cs), Long.class);
    		if(fields.get(field.getName()) instanceof String){
    			fields.put(field.getName(), Long.parseLong((String) fields.get(field.getName())));
    		}
    	}
        //将方法装到map中
        Map<Object,Object> returnMap = Maps.newHashMap();
        returnMap.putAll(fields);
        returnMap.put("setMethod", m);
        return returnMap;
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
	/**
	 * 策略:1.获取当前类的fieldMap
	 * 	   2.用fieldsMap 的 key 找到fieldMap中对应的field
	 * 	   3.用field找到set方法
	 * 	   4.使用set方法装配对象
	 * 	   5.返回对象 
	 * @param clazz
	 * @param t
	 * @param fieldsMap
	 * @return
	 * @throws SecurityException 
	 * @throws Exception 
	 */
	public static <T>T updateEntityByFieldMap(Class<?> clazz,T t,Map<Object, Object> fieldsMap) throws Exception{
		//获得迭代器
		Iterator<Map.Entry<Object,Object>> it =fieldsMap.entrySet().iterator();  
		//获得fieldMap
		Map<String, Field> fieldMap = getFieldMap(clazz);
		//装配对象
		try {
			while(it.hasNext()){
				Object key = it.next().getKey();
				if("id".equals(key)){
					continue;
				}
				Field field = fieldMap.get((String)key);
				Map<Object,Object> invokeMap = getMethodAndCheckValid(field,clazz ,fieldsMap);				
				Method setMethod = (Method) invokeMap.get("setMethod");				
				setMethod.invoke(t, invokeMap.get(field.getName()));
			}
			return t;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
		
	
}
