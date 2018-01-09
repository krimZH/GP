package cn.krim.gp.front.micService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.krim.gp.front.users.User;

@Component
@FeignClient("krim-gp-core")
public interface LoginFeignService {
	
	@RequestMapping(name="/core/login",method=RequestMethod.POST)
	public User loginFeign(@RequestParam("userId")String userId,@RequestParam("password")String password);
	
}
