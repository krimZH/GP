package cn.krim.gp.front.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.krim.gp.front.config.BeanMap;
import cn.krim.gp.front.micService.CrudService;
import cn.krim.gp.front.utils.MyReflectUtils;

@Controller
public class CrudController {
	
	@Autowired CrudService crudService;
	
	

	@RequestMapping(value="/findById",method=RequestMethod.POST)
	@ResponseBody
	public Object findById(String id,String entity,@RequestBody String jsonStr) throws Exception {
		Map<Object, Object> fieldMap = MyReflectUtils.fieldsMap(BeanMap.FRONT_BEAN_ADDR.get(entity), jsonStr);
		
		return crudService.findById(fieldMap,BeanMap.CORE_BEAN_ADDR.get(entity), entity);
	}
	
	
}
