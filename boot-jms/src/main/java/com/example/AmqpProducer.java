package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IBM on 2016/8/22.
 * amqp(RabbitMQ) 生产者
 */
@Component
public class AmqpProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(String msg){
        this.rabbitTemplate.convertAndSend(this.queue.getName(),msg);
    }
}
