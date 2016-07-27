package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HellobootApplication {

	@RequestMapping(value="/",method= RequestMethod.GET)
	@ResponseBody
	public String sayHello(){
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
