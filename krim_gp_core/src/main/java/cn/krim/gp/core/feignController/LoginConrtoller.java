package cn.krim.gp.core.feignController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.core.model.users.User;
import cn.krim.gp.core.service.LoginService;

@RestController
public class LoginConrtoller {
	
	@Autowired LoginService	loginService;
	
	@RequestMapping(value="/core/login",method=RequestMethod.POST)
	public User Login(@RequestParam("userId")String userId,@RequestParam("password")String password){
		
		return loginService.findUserByUserIdAndPassword(userId, password);
	}
}
