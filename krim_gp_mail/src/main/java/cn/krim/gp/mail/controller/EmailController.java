package cn.krim.gp.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.mail.model.MailTemplate;
import cn.krim.gp.mail.model.ReturnData;
import cn.krim.gp.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@RestController("/Email")
@Slf4j
public class EmailController {
	
	@Autowired EmailService emailService;
	
	@RequestMapping(name="/simpleEmail")
	public ReturnData sendSimpleEmail(@RequestBody MailTemplate mt){
		try {
			String to = mt.getAccepter();
			String titel  = mt.getTitle();
			String content= mt.getContent();
			log.info(mt.toString());
			emailService.sendSimpleMail(to, titel, content);
			return new ReturnData(null,"成功",200);
		} catch (Exception e) {
			return new ReturnData(null,e.getMessage(),700);
		}
	}
	
}
