package cn.krim.gp.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.krim.gp.front.constants.ResponseCode;
import cn.krim.gp.front.micService.MailService;
import cn.krim.gp.front.model.ReturnData;
import cn.krim.gp.front.model.http.MailTemplate;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MailController {
	
	@Autowired MailService mailService;
	
	@RequestMapping(value="/sendMail")
	public ReturnData sendMail(@RequestBody MailTemplate mt){
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
	
}
