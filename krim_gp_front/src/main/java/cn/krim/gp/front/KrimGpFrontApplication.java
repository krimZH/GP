package cn.krim.gp.front;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients(basePackages={"cn.krim.gp.front.micService"})
@SpringCloudApplication
public class KrimGpFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrimGpFrontApplication.class, args);
	}
}
