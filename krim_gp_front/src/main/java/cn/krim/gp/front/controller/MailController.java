package cn.krim.gp.front.controller;

import java.time.Clock;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import cn.krim.gp.front.config.BeanMap;
import cn.krim.gp.front.constants.ResponseCode;
import cn.krim.gp.front.micService.CrudService;
import cn.krim.gp.front.micService.MailService;
import cn.krim.gp.front.model.ReturnData;
import cn.krim.gp.front.model.http.MailTemplate;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MailController {
	
	@Autowired MailService mailService;
	@Autowired CrudService crudService;
	@RequestMapping(value="/sendMail")
	public ReturnData sendMail(MailTemplate mt){
		try{
			// mt =(MailTemplate) JSON.parse(json);
			Assert.notNull(mt.getTitle(), "邮件标题不能为空");
			Assert.notNull(mt.getAccepter(), "邮件接收人不能为空");
			Assert.notNull(mt.getContent(), "邮件内容不能为空");
			Assert.notNull(mt.getPublisher(), "邮件发布者不能为空");
			return mailService.sendMail(mt);
		}catch(Exception e){
			log.error(e.getMessage());
			return new ReturnData(null,String.format("can not send mail cause:%s",e.getMessage()),ResponseCode.MAIL_SEND_ERROR);
		}
	}
	
	@RequestMapping(value="/validMail",method=RequestMethod.POST)
	public ReturnData validMail(String id,String mail){
		try{
			Map<Object, Object> fieldsMap = Maps.newHashMap();
			fieldsMap.put("id", id);
			fieldsMap.put("mail", mail);
			fieldsMap.put("updateTime", Clock.systemDefaultZone().millis());
			return crudService.updateEntity(fieldsMap, BeanMap.CORE_BEAN_ADDR.get("User"), "User");
		}catch(Exception e){
			return new ReturnData(null,e.getMessage(),ResponseCode.METHOD_INVOKED_ERROR);
		}
	}
	
}
