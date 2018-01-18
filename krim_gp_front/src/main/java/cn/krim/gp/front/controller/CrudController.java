package cn.krim.gp.front.controller;

import java.time.Clock;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.krim.gp.front.config.BeanMap;
import cn.krim.gp.front.constants.ResponseCode;
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
			if(StringUtils.isEmpty(path)) return new ReturnData(null,"安全验证出错,请确认您正在通过正当的方式进行访问",ResponseCode.AUTH_FAILED_ERROR);
			return crudService.findByContitions(fieldMap,path, entity);
		} catch (Exception e) {
			return new ReturnData(null, e.getMessage(), ResponseCode.METHOD_INVOKED_ERROR);
		}
	}
	@RequestMapping(value="/saveEntity",method=RequestMethod.POST)
	@ResponseBody
	public ReturnData saveEntity(@RequestParam("fieldName[]") Object[] fieldName,@RequestParam("fieldValue[]")Object[] fieldValue,@RequestParam("entity")String entity){
		try {
			Assert.notEmpty(fieldName,"fieldName数据丢失");
			Assert.notEmpty(fieldValue,"fieldValue数据丢失");
			Map<Object, Object> fieldsMap = Maps.newHashMap();
			for (int i = 0; i < fieldValue.length; i++) {
				fieldsMap.put(fieldName[i], fieldValue[i]);
			}
			fieldsMap.put("createTime", Clock.systemDefaultZone().millis());			
			String path = BeanMap.CORE_BEAN_ADDR.get(entity);
			if(StringUtils.isEmpty(path)) return new ReturnData(null,"安全验证出错,请确认您正在通过正当的方式进行访问",ResponseCode.AUTH_FAILED_ERROR);
			return crudService.saveEntity(fieldsMap, path, entity);
		} catch (Exception e) {
			return new ReturnData(null, e.getMessage(), ResponseCode.METHOD_INVOKED_ERROR);
		}
	}
	@RequestMapping(value="/updateEntity",method=RequestMethod.POST)
	@ResponseBody
	public ReturnData updateEntity(HttpServletRequest request,@RequestParam("fieldName[]") Object[] fieldName,@RequestParam("fieldValue[]")Object[] fieldValue,@RequestParam("entity")String entity){
		try {
			//组装map
			Assert.notEmpty(fieldName,"fieldName数据丢失");
			Assert.notEmpty(fieldValue,"fieldValue数据丢失");
			Map<Object,Object> fieldsMap = Maps.newHashMap();
			for (int i = 0; i < fieldValue.length; i++) {
				fieldsMap.put(fieldName[i], fieldValue[i]);
			}
			fieldsMap.put("updateTime", Clock.systemDefaultZone().millis());
			String path = BeanMap.CORE_BEAN_ADDR.get(entity);
			if(StringUtils.isEmpty(path)) return new ReturnData(null,"安全验证出错,请确认您正在通过正当的方式进行访问",ResponseCode.AUTH_FAILED_ERROR);
			ReturnData rd= crudService.updateEntity(fieldsMap, path, entity);
			//刷新缓存
			if(rd.getCode()<300){
				request.getSession().setAttribute(entity, rd.getData());
				log.info(String.format("更新缓存:%s", entity));
			}
			return rd;
		} catch (Exception e) {
			return new ReturnData(null,e.getMessage(),ResponseCode.METHOD_INVOKED_ERROR);
		}
	}
}
