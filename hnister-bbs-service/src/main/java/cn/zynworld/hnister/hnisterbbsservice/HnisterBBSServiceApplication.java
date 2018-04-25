package cn.zynworld.hnister.hnisterbbsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.zynworld.hnister.hnisterbbsservice.mappers")
public class HnisterBBSServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterBBSServiceApplication.class, args);
	}
}
