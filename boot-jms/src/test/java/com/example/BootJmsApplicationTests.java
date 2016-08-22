package com.example;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 异步消息测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootJmsApplicationTests {

	@Rule
	public OutputCapture capture =new OutputCapture();//输出对象检测

	@Autowired
	private JmsProducer producer;//生产者

	/**
	 * jms 消息生产、消费测试
	 * @throws InterruptedException
     */
	@Test
	public void JmsMessageTest() throws InterruptedException {
		String  msg="jmsTestMsg";
		Assertions.assertThat(producer).isNotNull();
		producer.send(msg);//发送消息
		Assertions.assertThat(capture.toString()).contains(msg);//验证
	}

}
