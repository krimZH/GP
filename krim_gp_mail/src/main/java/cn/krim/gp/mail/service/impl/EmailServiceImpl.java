package cn.krim.gp.mail.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.krim.gp.mail.config.EmailConfig;
import cn.krim.gp.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired  
    private EmailConfig emailConfig;
    @Autowired  
    private JavaMailSender sender; 
    @Autowired  
    private JavaMailSender mailSender;  	
      
    public void sendSimpleMail(String sendTo, String titel, String content) throws MessagingException {  
       String[] mailToAddress = sendTo.split(",");
       Assert.notEmpty(mailToAddress,"收件人为空");
       SimpleMailMessage[] smms = new SimpleMailMessage[mailToAddress.length];
       for (int i = 0; i < smms.length; i++) {
		smms[i] = new SimpleMailMessage();
		smms[i].setFrom(emailConfig.getEmailFrom());
		smms[i].setSubject(titel);
		smms[i].setTo(mailToAddress[i]);
		smms[i].setText(content);
       }
       try {
    	   log.info(String.format("send to %s,titel %s,content %s", sendTo,titel,content));
		   mailSender.send(smms);;
		} catch (MailException e) {
			log.error(e.getMessage());
		}  
    }  
  
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {  
    	 MimeMessage message = sender.createMimeMessage();  
    	  
         try {  
             //true表示需要创建一个multipart message  
             MimeMessageHelper helper = new MimeMessageHelper(message, true);  
             helper.setFrom(emailConfig.getEmailFrom());  
             helper.setTo(to);  
             helper.setSubject(subject);  
             helper.setText(content, true);  
   
             FileSystemResource file = new FileSystemResource(new File(filePath));  
             String fileName = filePath.substring(filePath.lastIndexOf(File.separator));  
             helper.addAttachment(fileName, file);  
               
             sender.send(message);  
             log.info("带附件的邮件已经发送。");  
         } catch (MessagingException e) {  
             log.error("发送带附件的邮件时发生异常！", e);  
         }  
    }
}