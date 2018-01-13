package cn.krim.gp.front.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.krim.gp.front.config.BeanMap;
import cn.krim.gp.front.micService.CrudService;
import cn.krim.gp.front.model.ReturnData;
import cn.krim.gp.front.utils.MyReflectUtils;

@Controller
public class CrudController {
	
	@Autowired CrudService crudService;
	
	@RequestMapping(value="/findByConditions",method=RequestMethod.POST)
	@ResponseBody
	public ReturnData findByContitions(String entity,@RequestBody String jsonStr){
		Map<String, Object[]> fieldMap;
		try {
			fieldMap = MyReflectUtils.map2Array(jsonStr);
			String path = BeanMap.CORE_BEAN_ADDR.get(entity);
			if(StringUtils.isEmpty(path)) throw new Exception("安全验证出错,请确认您正在通过正当的方式进行访问");
			return crudService.findByContitions(fieldMap,path, entity);
		} catch (Exception e) {
			return new ReturnData(null, e.getMessage(), 500);
		}
	}
	
}
