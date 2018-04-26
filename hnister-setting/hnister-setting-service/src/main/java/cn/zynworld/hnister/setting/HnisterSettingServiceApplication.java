package cn.zynworld.hnister.setting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.setting.mappers")
public class HnisterSettingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterSettingServiceApplication.class, args);
	}
}
