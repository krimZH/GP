package cn.krim.gp.core.service;

import cn.krim.gp.core.model.users.User;

public interface LoginService {
	
	public User findUserByUserIdAndPassword(String userId,String password);
	
}
