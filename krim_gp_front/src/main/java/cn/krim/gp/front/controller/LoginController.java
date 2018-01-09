package cn.krim.gp.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.krim.gp.front.micService.LoginFeignService;
import cn.krim.gp.front.users.User;

@Controller
public class LoginController {
	
	@Autowired LoginFeignService loginFeignService;
	
	@RequestMapping("/vaildLogin")
	@ResponseBody
	public String login(@RequestParam("login-name") String userId,@RequestParam("login-pass")String password){
		String result = null;
		User u = loginFeignService.loginFeign(userId, password);
		if(u!=null){
			result = "success";
		}else{
			result = "faild";
		}
		return result;
	}
	
	@RequestMapping("/")
	public String index(){
		return "login";
	}
}
