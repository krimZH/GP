package cn.krim.gp.front.controller;

import java.time.Clock;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.krim.gp.front.config.BeanMap;
import cn.krim.gp.front.micService.CrudService;
import cn.krim.gp.front.model.ReturnData;
import cn.krim.gp.front.utils.MyReflectUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CrudController {
	
	@Autowired CrudService crudService;
	
	@RequestMapping(value="/findByConditions",method=RequestMethod.POST)
	@ResponseBody
	public ReturnData findByContitions(@RequestParam("entity")String entity,@RequestParam("jsonStr") String jsonStr){
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
	@RequestMapping(value="/saveEntity",method=RequestMethod.POST)
	@ResponseBody
	public ReturnData saveEntity(@RequestParam("fieldName[]") Object[] fieldName,@RequestParam("fieldValue[]")Object[] fieldValue,@RequestParam("entity")String entity,@RequestParam(name="create",required=false)String create){
		try {
			Assert.notEmpty(fieldName,"fieldName数据丢失");
			Assert.notEmpty(fieldValue,"fieldValue数据丢失");
			Map<Object, Object> fieldsMap = Maps.newHashMap();
			for (int i = 0; i < fieldValue.length; i++) {
				fieldsMap.put(fieldName[i], fieldValue[i]);
			}
			if(create!=null){
				//新增
				fieldsMap.put("createTime", Clock.systemDefaultZone().millis());
			}else{
				//更改
				fieldsMap.put("updateTime", Clock.systemDefaultZone().millis());
			}
			
			String path = BeanMap.CORE_BEAN_ADDR.get(entity);
			if(StringUtils.isEmpty(path)) throw new Exception("安全验证出错,请确认您正在通过正当的方式进行访问");
			return crudService.saveEntity(fieldsMap, path, entity);
		} catch (Exception e) {
			return new ReturnData(null, e.getMessage(), 500);
		}
	}
}
