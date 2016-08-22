package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动
 */
@SpringBootApplication
@EnableRabbit
public class BootJmsApplication implements CommandLineRunner {

	@Autowired
	private AmqpProducer amqpProducer;

	public static void main(String[] args) {
		SpringApplication.run(BootJmsApplication.class, args);
	}


	/**
	 * 自定义目的序列
	 * @return		序列实体
	 */
	@Bean
	public Queue queue(){
		return new Queue("my-destination");
	}

	/**
	 * 启动方法
	 * @param strings		参数
	 * @throws Exception
     */
	@Override
	public void run(String... strings) throws Exception {
		amqpProducer.send("测试消息");
	}
}
