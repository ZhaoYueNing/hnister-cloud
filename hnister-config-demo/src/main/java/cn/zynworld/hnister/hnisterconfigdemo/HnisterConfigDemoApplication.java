package cn.zynworld.hnister.hnisterconfigdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class HnisterConfigDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnisterConfigDemoApplication.class, args);
	}

	@Value("${demo.info}")
	private String demoInfo;
	@GetMapping(path="demoInfo")
	public String getDemoInfo(){
		return demoInfo;
	}
}
