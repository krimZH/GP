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
import cn.krim.gp.core.utils.MyReflectUtils;
/**
 * CURD controller  execute simple CRUD operating uniformly
 * @author krim
 *
 */
@RestController
public class CrudController <T extends MyEntity> {
	@Autowired CrudService crudService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/core/create",method=RequestMethod.POST)
	public T saveEntity(@RequestBody Map<Object, Object> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity) throws Exception{
		try {
			Class<?> clazz = Class.forName(path);
			T t = (T)MyReflectUtils.getInstaceAndSetFields(clazz, fieldMap);
			return crudService.saveEntity((T)t, entity);
		} catch (Exception e) {
			throw new Exception(String.format("create entity error cause:%s", e.getMessage()));
		}
	}	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/core/delete",method=RequestMethod.POST)
	public void deleteEntity(@RequestBody Map<Object, Object> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity) throws Exception{
		try {
			Class<?> clazz = Class.forName(path);
			T t = (T)MyReflectUtils.getInstaceAndSetFields(clazz, fieldMap);
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
}
