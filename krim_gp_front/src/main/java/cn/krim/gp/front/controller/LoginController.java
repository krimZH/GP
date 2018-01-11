package cn.krim.gp.front.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.krim.gp.front.micService.LoginFeignService;
import cn.krim.gp.front.users.User;

@Controller
public class LoginController {
	
	@Autowired LoginFeignService loginFeignService;
	
	@RequestMapping("/vaildLogin")
	public String login(@RequestParam("login-name") String userId,@RequestParam("login-pass")String password,Model model){
		String result = null;
		User u = loginFeignService.loginFeign(userId, password);
		if(u!=null){
			result = "console";
		}else{
			model.addAttribute("errMsg", "用户名或者密码错误");
			result = "login";
		}
		return result;
	}
	
	@RequestMapping("/{path}")
	public String test(@PathParam("path")String path){
		return path;
	}
	
}
