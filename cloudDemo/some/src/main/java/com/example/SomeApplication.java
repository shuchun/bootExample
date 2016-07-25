package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 其他服务
 */
@SpringBootApplication
@EnableDiscoveryClient		//对外服务客户端
@RestController
public class SomeApplication {

	@Value("${my.message}")//通过配置中心配置数值
	private String message;

	/**
	 * 提供对外服务，输出一段信息。
	 * @return
     */
	@RequestMapping(value="/getsome")
	public String getsome(){
		return message;
	}
	public static void main(String[] args) {
		SpringApplication.run(SomeApplication.class, args);
	}
}
