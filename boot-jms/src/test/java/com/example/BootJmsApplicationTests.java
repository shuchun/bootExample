package com.example;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.context.Theme;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootJmsApplicationTests {

	@Rule
	public OutputCapture capture=new OutputCapture();

	@Autowired
	private AmqpProducer producer;

	@Test
	public void sendMsgTest() throws InterruptedException {
		String msg="amqpMsgTest";
		Assertions.assertThat(producer).isNotNull();
		producer.send(msg);
		Thread.sleep(1000L);
		Assertions.assertThat(capture.toString()).contains(msg);
	}

}
