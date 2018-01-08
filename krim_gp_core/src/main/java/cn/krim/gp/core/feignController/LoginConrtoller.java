package cn.krim.gp.core.feignController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.core.service.LoginService;
import cn.krim.gp.entities.users.User;

@RestController
public class LoginConrtoller {
	
	@Autowired LoginService	loginService;
	
	@RequestMapping("/login")
	public User Login(String userId,String password){
		
		return loginService.findUserByUserIdAndPassword(userId, password);
	}
}
