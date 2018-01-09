package cn.krim.gp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.netflix.ribbon.proxy.annotation.Hystrix;

@Hystrix
@EnableEurekaServer
@SpringBootApplication
public class KrimGpEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrimGpEurekaApplication.class, args);
	}
}
