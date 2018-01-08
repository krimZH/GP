package cn.krim.gp.mail.service;

/**
 * Email 
 * @author krim
 *
 */
public interface EmailService {
	/** 
     * 发送简单邮件 
     * @param sendTo 收件人地址 
     * @param titel  邮件标题 
     * @param content 邮件内容 
     */  
    public void sendSimpleMail(String sendTo, String titel, String content);  
      
    /** 
     * 发送带附件的简单邮件 
     * @param sendTo 收件人地址 
     * @param titel  邮件标题 
     * @param content 邮件内容 
     * @param attachments<文件名，附件> 附件列表 
     */  
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);  
         
}
