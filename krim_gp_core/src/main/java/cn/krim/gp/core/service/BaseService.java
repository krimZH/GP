package cn.krim.gp.core.service;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import cn.krim.gp.core.model.MyEntity;

public interface BaseService {
	
	
	public <T extends MyEntity> Specification<T> getConditions(Class<?> clazz,Map<String, Object[]> entityMap) throws Exception;
}
