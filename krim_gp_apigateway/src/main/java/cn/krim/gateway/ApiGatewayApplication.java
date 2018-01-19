package cn.krim.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.FilterProcessor;

import cn.krim.gateway.filter.processor.MyFilterProcessor;

@EnableZuulProxy
@SpringBootApplication
@EnableHystrix
public class ApiGatewayApplication {

	public static void main(String[] args) {
		FilterProcessor.setProcessor(new MyFilterProcessor());
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties(){
		return new ZuulProperties();
	}
	
}
