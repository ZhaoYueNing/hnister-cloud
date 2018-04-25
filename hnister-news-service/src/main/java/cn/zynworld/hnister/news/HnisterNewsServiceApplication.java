package cn.zynworld.hnister.news;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zynworld.hnister.news.mappers")
public class HnisterNewsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterNewsServiceApplication.class, args);
	}
}
