package cn.krim.gp.core;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.krim.gp.core.model.users.User;
import cn.krim.gp.core.repository.UserRespository;
import cn.krim.gp.core.repository.base.SimpleSpecificationBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KrimGpCoreApplicationTests {
	@Autowired UserRespository userRespository; 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void contextLoads() {
		List<User> list=userRespository.findAll(new SimpleSpecificationBuilder("status","=","1").and("className", "=", "光信1401").generateSpecification());
		System.out.println(list);
	}

}
