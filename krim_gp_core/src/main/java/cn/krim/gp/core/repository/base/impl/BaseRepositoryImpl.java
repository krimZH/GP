package cn.krim.gp.core.repository.base.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import cn.krim.gp.core.repository.base.BaseRepository;
import cn.krim.gp.core.utils.DButils;

public class BaseRepositoryImpl<T,ID extends Serializable>  extends SimpleJpaRepository<T, Serializable>  implements BaseRepository<T, Serializable> {
		
	/**
	 * 持久化上下文
	 */
	@SuppressWarnings("unused")
	private EntityManager entityManager;
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager=em;
	}

	@Override
	public int updateEntity(Map<Object, Object> fieldMap, Class<?> entity) throws Exception {
		StringBuffer sqlsb = new StringBuffer();
		sqlsb.append("UPDATE ").append(DButils.getTableName(entity)+" SET ");
		for (Field field : entity.getDeclaredFields()) {
			//寻找需要跟新的字段
			if(fieldMap.containsKey(field.getName())){
				sqlsb.append(DButils.getColumn(field)+" = "+fieldMap.get(field.getName()));
			}
		}
		//所有跟新的主键在map中都设为id
		sqlsb.append(" WHERE "+DButils.getId(entity)+" = "+fieldMap.get("id"));
		Query query = entityManager.createNativeQuery(sqlsb.toString());
		return query.executeUpdate();
	}



}
