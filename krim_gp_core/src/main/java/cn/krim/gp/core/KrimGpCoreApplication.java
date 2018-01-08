package cn.krim.gp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan("cn.krim.gp.entities.*")
@SpringBootApplication
public class KrimGpCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrimGpCoreApplication.class, args);
	}
}
