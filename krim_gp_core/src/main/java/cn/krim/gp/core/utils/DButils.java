package cn.krim.gp.core.utils;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;


public class DButils {
	
	public static String getTableName(Class<?> clazz) throws Exception{
		Table table = clazz.getAnnotation(Table.class);
		if (table==null) 
			throw new Exception(String.format("not found table with:%s",clazz.getName()));
		String tableName = table.name();
		if (StringUtils.isEmpty(tableName)) 
			throw new Exception(String.format("not found table with:%s",clazz.getName()));
		return tableName;
	}
	public static String getId(Class<?> clazz){
		
		for (Field field : clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(Id.class)){
				Column id = field.getDeclaredAnnotation(Column.class);
				return id.name();
			}
		}
		return null;
	}
	
	public static String getColumn(Field field){
		Column colum = field.getDeclaredAnnotation(Column.class);
		return colum.name();	
	}
}
