package cn.krim.gp.core.coreController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.core.model.AbstratEntity;
import cn.krim.gp.core.service.impl.CrudService;
import cn.krim.gp.core.utils.MyReflectUtils;
import lombok.extern.slf4j.Slf4j;
/**
 * CURD controller  execute simple CRUD operating uniformly
 * @author krim
 *
 */
@Slf4j
@RestController
public class CrudController <T extends AbstratEntity> {
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
	@RequestMapping(value="/core/findById",method=RequestMethod.POST)
	public T findEntityById(@RequestBody Map<Object, Object> fieldMap,@RequestParam("entityPath")String path,@RequestParam("entity")String entity) throws Exception{
		try {
			Class<?> clazz = Class.forName(path);
			T t = (T)MyReflectUtils.getInstaceAndSetFields(clazz, fieldMap);
			t = crudService.findEntityById((T)t, entity);
			log.info(t.toString());
			return t;
		} catch (Exception e) {
			throw new Exception(String.format("select entity error cause:%s", e.getMessage()));
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
}
