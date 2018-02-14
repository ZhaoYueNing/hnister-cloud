package cn.zynworld.hnister.schoolmate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.common.mappers")
public class HnisterSchoolmateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterSchoolmateServiceApplication.class, args);
	}
}
