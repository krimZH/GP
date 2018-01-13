package cn.krim.gp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cn.krim.gp.core.repository.base.BaseRepositoryFactoryBean;

@EnableFeignClients(basePackages={"cn.krim.gp.core.coreController"})
@EnableJpaRepositories(basePackages={"cn.krim.gp.core.repository"},
					   repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@SpringCloudApplication
public class KrimGpCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrimGpCoreApplication.class, args);
	}
}
