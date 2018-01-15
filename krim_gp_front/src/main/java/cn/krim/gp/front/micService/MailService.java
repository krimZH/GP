package cn.krim.gp.front.micService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.krim.gp.front.model.ReturnData;
import cn.krim.gp.front.model.http.MailTemplate;

@Component
@FeignClient("krim-gp-mail")
public interface MailService {
	
	@RequestMapping(value="/Email/simpleEmail",method=RequestMethod.POST)
	public ReturnData sendMail(@RequestBody MailTemplate mt);
}
