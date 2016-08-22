package com.example;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * 启动
 */
@SpringBootApplication
@EnableJms
public class BootJmsApplication implements CommandLineRunner {

	@Autowired
	private JmsProducer jmsProducer;

	public static void main(String[] args) {
		SpringApplication.run(BootJmsApplication.class, args);
	}

	/**
	 * 自定义目的序列
	 * @return		序列实体
     */
	@Bean
	public Queue queue(){
		return  new ActiveMQQueue("my-destination");
	}

	/**
	 * 启动方法
	 * @param strings		参数
	 * @throws Exception
     */
	@Override
	public void run(String... strings) throws Exception {
		jmsProducer.send("测试消息");
	}
}
