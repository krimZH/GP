package cn.krim.gp.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.krim.gp.core.repository.UserRespository;
import cn.krim.gp.core.service.LoginService;
import cn.krim.gp.entities.users.User;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired UserRespository	userRespository;
	
	@Override
	public User findUserByUserIdAndPassword(String userId, String password) {
		
		return userRespository.findOneByUserIdAndPassword(userId, password);
	}

}
