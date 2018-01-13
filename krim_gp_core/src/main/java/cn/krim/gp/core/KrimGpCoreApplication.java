package cn.krim.gp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients(basePackages={"cn.krim.gp.core.coreController"})
@SpringCloudApplication
public class KrimGpCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrimGpCoreApplication.class, args);
	}
}
