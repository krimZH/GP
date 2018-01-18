package cn.krim.gp.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsoleController {
	
	@RequestMapping(value="/User/create")
	public String createUser(){
		return "User/NewUser";
	}
	
}
