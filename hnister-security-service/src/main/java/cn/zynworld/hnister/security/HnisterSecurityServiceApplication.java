package cn.zynworld.hnister.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.common.mappers")
public class HnisterSecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterSecurityServiceApplication.class, args);
	}
}
