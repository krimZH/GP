package cn.krim.gp.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.krim.gp.front.micService.LoginFeignService;
import cn.krim.gp.front.model.users.User;

@Controller
public class LoginController {
	
	@Autowired LoginFeignService loginFeignService;
	
	@RequestMapping("/validLogin")
	public String login(HttpServletRequest request,@RequestParam("login-name") String userId,@RequestParam("login-pass")String password,Model model){
		String result = null;
		User u = loginFeignService.loginFeign(userId, password);
		if(u!=null){
			request.getSession().setAttribute("User", u);
			result = "console";
		}else{
			model.addAttribute("errMsg", "用户名或者密码错误");
			result = "login";
		}
		return result;
	}
	
	@RequestMapping(value="/login")
	public String getLoginPage(){
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute("User");
		return "login";
	}
}
