package cn.krim.gp.core.CRUDController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.krim.gp.core.repository.UserRespository;
import cn.krim.gp.core.utils.MyReflectUtils;
import cn.krim.gp.entities.users.User;

@RestController("/CRUD")
public class CRUDController {
	@Autowired UserRespository	userRespository;
	
	@RequestMapping(name="/create",method=RequestMethod.POST)
	public <T>T create(Class<T> clz,Map<String, Object> fields){
		try {
			MyReflectUtils.getInstaceAndSetFields(clz, fields);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return null;
	}
	
	@RequestMapping(name="/delete",method=RequestMethod.DELETE)
	public void delete(User user){
		userRespository.delete(user);
	}
}
