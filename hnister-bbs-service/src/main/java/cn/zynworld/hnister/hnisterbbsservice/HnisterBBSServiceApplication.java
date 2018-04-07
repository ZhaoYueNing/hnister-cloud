package cn.zynworld.hnister.hnisterbbsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.common.mappers")
public class HnisterBBSServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterBBSServiceApplication.class, args);
	}
}
