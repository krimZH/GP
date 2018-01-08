package cn.krim.gp.mail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@RestController("/Email")
@Slf4j
public class EmailController {
	
	@Autowired EmailService emailService;
	
	@RequestMapping(name="/simpleEmail")
	public void sendSimpleEmail(HttpServletRequest request,HttpServletResponse response){
		String sendTos = request.getParameter("sendTo");
		String[] sendTo = sendTos.split(",");
		String titel  = request.getParameter("titel");
		String content= request.getParameter("content");
		if(sendTo.length>1){
			for (String to : sendTo) {
				emailService.sendSimpleMail(to, titel, content);
				log.info(String.format("send to %s,titel %s,content %s", to,titel,content));
			}
		}else{
			emailService.sendSimpleMail(sendTos, titel, content);
		}
	}
	
}
