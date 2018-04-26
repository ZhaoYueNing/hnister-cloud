package cn.zynworld.hnister.security;

import cn.zynworld.hnister.common.constants.ServiceApiPackageConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cn.zynworld.hnister"})
@MapperScan("cn.zynworld.hnister.common.mappers")
@EnableFeignClients(basePackages = {ServiceApiPackageConstant.HNISTER_SCHOOLMATE_API})
public class HnisterSecurityServiceApplication {
	public static void main( String[] args) {
		SpringApplication.run(HnisterSecurityServiceApplication.class, args);
	}
}
