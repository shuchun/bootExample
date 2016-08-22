package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by IBM on 2016/8/22.
 * 消息生产者
 */
@Component
public class JmsProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    /**
     * 生产者发送消息
     * @param msg       消息实体
     */
    public void send(String msg){
        //this.jmsMessagingTemplate.convertAndSend("my-destination",msg);//根据队列名，自动使用默认队列
        this.jmsMessagingTemplate.convertAndSend(this.queue,msg);//使用自定义目的队列
    }
}
