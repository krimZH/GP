package cn.krim.gp.mail.config;
/**
 * E-mail 配置导入
 * @author krim
 *
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmailConfig {
	
	 @Value("${spring.mail.username}")  
	 private String emailFrom;  
}
