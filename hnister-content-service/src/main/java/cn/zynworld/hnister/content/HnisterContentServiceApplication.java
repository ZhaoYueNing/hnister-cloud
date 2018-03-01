package cn.zynworld.hnister.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.common.mappers")
public class HnisterContentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterContentServiceApplication.class, args);
	}
}
