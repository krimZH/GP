package cn.krim.gp.core.service.impl;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.krim.gp.core.annotation.Fuzzy;
import cn.krim.gp.core.model.MyEntity;
import cn.krim.gp.core.repository.base.SimpleSpecificationBuilder;
import cn.krim.gp.core.service.BaseService;
import cn.krim.gp.core.utils.MyReflectionUtils;

@Service
public class BaseServiceImpl implements BaseService {


	@Override
	public <T extends MyEntity> Specification<T> getConditions(Class<?> clazz,Map<String, Object[]> entityMap) throws Exception {
		SimpleSpecificationBuilder<T> ssb = new SimpleSpecificationBuilder<>();
		//获得该类的所有字段映射
		Map<String, Field> fieldMap = MyReflectionUtils.getFieldMap(clazz);
		//对比两个map
		Object[] fieldName = entityMap.get("fieldName");
		Object[] fieldValue = entityMap.get("fieldValue");
		for (int i = 0; i < fieldValue.length; i++) {
			Field f = fieldMap.get(fieldName[i]);
			if(f==null) throw new Exception(String.format("field not found :%s",fieldName[i]));
			//使用like的field
			if(null!=f.getAnnotation(Fuzzy.class)&&f.getAnnotation(Fuzzy.class).isFuzzy()){
				ssb.and(f.getName(), ":", fieldValue[i]);
			}else{
				ssb.and(f.getName(), "=", fieldValue[i]);
			}
		}
		return ssb.generateSpecification();
	}

}
