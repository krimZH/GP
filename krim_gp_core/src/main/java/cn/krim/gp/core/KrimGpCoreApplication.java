package cn.krim.gp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.SpringCloudApplication;
@EntityScan("cn.krim.gp.entities.*")
@SpringCloudApplication
public class KrimGpCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrimGpCoreApplication.class, args);
	}
}
