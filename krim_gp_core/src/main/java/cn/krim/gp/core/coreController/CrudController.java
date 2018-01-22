package cn.krim.gp.core.coreController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.core.model.MyEntity;
import cn.krim.gp.core.model.ReturnData;
import cn.krim.gp.core.service.impl.CrudService;
import cn.krim.gp.core.utils.MyReflectionUtils;
/**
 * CURD controller  execute simple CRUD operating uniformly
 * @author krim
 *
 */
@RestController
public class CrudController <T extends MyEntity> {
	@Autowired CrudService crudService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/core/save",method=RequestMethod.POST)
	public ReturnData saveEntity(@RequestBody Map<Object, Object> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity) throws Exception{
		try {
			Class<?> clazz = Class.forName(path);
			T t = (T)MyReflectionUtils.getInstaceAndSetFields(clazz, fieldMap);
			return new ReturnData(crudService.saveEntity((T)t, entity),"成功",200);
		} catch (Exception e) {
			return new ReturnData(null,String.format("create entity error cause:%s", e.getMessage()),700);
		}
	}	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/core/delete",method=RequestMethod.POST)
	public void deleteEntity(@RequestBody Map<Object, Object> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity) throws Exception{
		try {
			Class<?> clazz = Class.forName(path);
			T t = (T)MyReflectionUtils.getInstaceAndSetFields(clazz, fieldMap);
			crudService.deleteEntity((T)t, entity);
		} catch (Exception e) {
			throw new Exception(String.format("delete entity error cause:%s", e.getMessage()));
		}
		
	}
	@RequestMapping(value="/core/findByConditions",method=RequestMethod.POST)
	public ReturnData findByConditions(@RequestBody Map<String, Object[]> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity) throws Exception{
		try {
			if(fieldMap==null||fieldMap.size()==0) throw new Exception("loss condition,permission deny");
			Class<?> clazz = Class.forName(path);
			List<T> data = crudService.findByConditions(clazz, fieldMap,entity);
			return new ReturnData(data, "成功", 200);
		} catch (ClassNotFoundException e) {
			return new ReturnData(null, e.getMessage(),500);
		}		
	}
	
	@RequestMapping(value="/core/updateEntity",method=RequestMethod.POST)
	public ReturnData updateEntity(@RequestBody Map<Object, Object> fieldsMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity){
		try {
			Class<?> clazz = Class.forName(path);
			return new ReturnData(crudService.updateEntity(clazz, fieldsMap,entity),"更新成功",200);
		} catch (Exception e) {
			return new ReturnData(null, e.getMessage(),500);
		}
	}
	
}
