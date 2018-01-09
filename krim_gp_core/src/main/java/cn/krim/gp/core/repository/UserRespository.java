package cn.krim.gp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.krim.gp.core.modle.users.User;

public interface UserRespository extends JpaRepository<User, Long> {
	public User findOneByUserIdAndPassword(String userId,String password);
	
	public List<User> findAllByStatus(Integer status);
}
