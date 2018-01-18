package cn.krim.gp.front.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonalController {
	
	@RequestMapping(value="/personal/message",method=RequestMethod.GET)
	public String getPersonalPage(){
		return "User/Personal";
	}
}
