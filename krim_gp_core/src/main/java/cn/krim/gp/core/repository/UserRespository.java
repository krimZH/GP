package cn.krim.gp.core.repository;

import java.util.List;

import cn.krim.gp.core.model.users.User;
import cn.krim.gp.core.repository.base.BaseRepository;

public interface UserRespository extends BaseRepository<User,String>{
	public User findOneByUserIdAndPassword(String userId,String password);
	
	public List<User> findAllByStatus(Integer status);
}
