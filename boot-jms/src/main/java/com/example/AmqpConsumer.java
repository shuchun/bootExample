package com.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by IBM on 2016/8/22.
 * amqp消费者
 */
@Component
public class AmqpConsumer {

    @RabbitListener(queues = "my-destination")
    public void receiver(String msg){
        System.out.println("收到JMS消息：<"+msg+">");//输出获取到的消息
    }
}
