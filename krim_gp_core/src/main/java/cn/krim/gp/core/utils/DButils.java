package cn.krim.gp.core.utils;

import javax.persistence.Table;

import org.springframework.util.StringUtils;

public class DButils {
	
	public String getTableName(Class<?> clazz) throws Exception{
		Table table = clazz.getAnnotation(Table.class);
		if (table==null) 
			throw new Exception(String.format("not found table with:%s",clazz.getName()));
		String tableName = table.name();
		if (StringUtils.isEmpty(tableName)) 
			throw new Exception(String.format("not found table with:%s",clazz.getName()));
		return tableName;
	}
	
}
