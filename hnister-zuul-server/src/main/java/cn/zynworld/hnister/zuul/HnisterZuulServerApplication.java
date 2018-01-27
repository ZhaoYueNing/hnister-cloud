package cn.zynworld.hnister.zuul;

import cn.zynworld.hnister.zuul.manager.RoleResourceManager;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableZuulProxy
@MapperScan("cn.zynworld.hnister.common.mappers")
//@EnableAutoConfiguration(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class HnisterZuulServerApplication {


	@Bean
	//	解决跨域问题
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		//允许跨域
		config.setAllowCredentials(true);
		//允许向该服务器提交请求的URI,*表示全部
		config.addAllowedOrigin("*");
		//允许访问的头信息,*表示全部
		config.addAllowedHeader("*");
		//允许的method
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		//免检时间,单位是秒
		//config.setMaxAge(3600)
		//Enabling CORS for the whole application
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	//实现资源角色缓存
	@Bean(initMethod = "init")
	public RoleResourceManager roleResourceManager(){
		return new RoleResourceManager();
	}


	public static void main(String[] args) {
		SpringApplication.run(HnisterZuulServerApplication.class, args);
	}
}
