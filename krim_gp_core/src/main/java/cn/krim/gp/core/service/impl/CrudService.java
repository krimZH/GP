package cn.krim.gp.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.krim.gp.core.functional.GetId;
import cn.krim.gp.core.model.MyEntity;
import cn.krim.gp.core.repository.CommonQuestionRepository;
import cn.krim.gp.core.repository.PictureQuestionRepository;
import cn.krim.gp.core.repository.TestPaperRepository;
import cn.krim.gp.core.repository.UserRespository;
import cn.krim.gp.core.repository.base.BaseRepository;
/**
 * CURD controller  execute simple CRUD operating uniformly
 * @author krim
 *
 */
@Service
public class CrudService extends BaseServiceImpl {
	@Autowired  UserRespository userRespository;
	@Autowired  CommonQuestionRepository commonQuestionRepository;
	@Autowired  PictureQuestionRepository pictureQuestionRepository;
	@Autowired  TestPaperRepository testPaperRepository;
	private static final Map<String, Object> REPOSITORYMap =Maps.newConcurrentMap();
	
	@SuppressWarnings("unchecked")
	public <T extends MyEntity>T saveEntity(T t,String entity) throws Exception{
		init();
		try {
			BaseRepository<T, Serializable> repository = (BaseRepository<T, Serializable>) REPOSITORYMap.get(entity);
			return repository.save(t);
		} catch (Exception e) {
			throw new Exception(String.format("create entity error cause:%s", e.getMessage()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends MyEntity> void deleteEntity(T t,String entity) throws Exception{
		init();
		try {
			BaseRepository<T, Serializable> repository = (BaseRepository<T, Serializable>) REPOSITORYMap.get(entity);
			repository.delete(t);
		} catch (Exception e) {
			throw new Exception(String.format("delete entity error cause:%s", e.getMessage()));
		}
		
	}
	@SuppressWarnings("unchecked")
	public <T extends MyEntity> List<T> findByConditions(Class<?> clazz,Map<String, Object[]> entityMap,String entity) throws Exception{
		init();
		BaseRepository<T, Serializable> repository = (BaseRepository<T, Serializable>) REPOSITORYMap.get(entity);
		Specification<T> s = getConditions(clazz, entityMap);		
		return repository.findAll(s);
	}
	private void init(){
		if(REPOSITORYMap.size()==0){
			REPOSITORYMap.put("User", userRespository);
			REPOSITORYMap.put("Common", commonQuestionRepository);
			REPOSITORYMap.put("Picture", pictureQuestionRepository);
			REPOSITORYMap.put("TestPaper", testPaperRepository);
		}
	}
}
