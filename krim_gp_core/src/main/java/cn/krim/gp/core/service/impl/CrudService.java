package cn.krim.gp.core.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.krim.gp.core.functional.GetId;
import cn.krim.gp.core.model.AbstratEntity;
import cn.krim.gp.core.repository.CommonQuestionRepository;
import cn.krim.gp.core.repository.PictureQuestionRepository;
import cn.krim.gp.core.repository.TestPaperRepository;
import cn.krim.gp.core.repository.UserRespository;
/**
 * CURD controller  execute simple CRUD operating uniformly
 * @author krim
 *
 */
@Service
public class CrudService {
	@Autowired  UserRespository userRespository;
	@Autowired  CommonQuestionRepository commonQuestionRepository;
	@Autowired  PictureQuestionRepository pictureQuestionRepository;
	@Autowired  TestPaperRepository testPaperRepository;
	private static final Map<String, Object> REPOSITORYMap =Maps.newConcurrentMap();
	
	@SuppressWarnings("unchecked")
	public <T extends AbstratEntity>T saveEntity(T t,String entity) throws Exception{
		init();
		try {
			JpaRepository<T, Serializable> repository = (JpaRepository<T, Serializable>) REPOSITORYMap.get(entity);
			return repository.save(t);
		} catch (Exception e) {
			throw new Exception(String.format("create entity error cause:%s", e.getMessage()));
		}
	}
	@SuppressWarnings("unchecked")
	public <T extends AbstratEntity>T findEntityById(T t,String entity) throws Exception{
		init();
		try {
			JpaRepository<T, Serializable> repository = (JpaRepository<T, Serializable>) REPOSITORYMap.get(entity);
			GetId y =t::getEntityId;
			return repository.findOne((Serializable) y.getEntityId());
		} catch (Exception e) {
			throw new Exception(String.format("select entity error cause:%s", e.getMessage()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AbstratEntity> void deleteEntity(T t,String entity) throws Exception{
		init();
		try {
			JpaRepository<T, Serializable> repository = (JpaRepository<T, Serializable>) REPOSITORYMap.get(entity);
			repository.delete(t);
		} catch (Exception e) {
			throw new Exception(String.format("delete entity error cause:%s", e.getMessage()));
		}
		
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
