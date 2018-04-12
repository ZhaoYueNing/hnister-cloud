package cn.zynworld.hnister.schoolmate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.common.mappers")
public class HnisterSchoolmateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterSchoolmateServiceApplication.class, args);
	}
}
