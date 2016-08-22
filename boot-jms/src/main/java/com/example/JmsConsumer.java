package com.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by IBM on 2016/8/22.
 * 消息消费监听
 */
@Component
public class JmsConsumer {

    /**
     * 使用监听器
     * @param msg
     */
    @JmsListener(destination = "my-destination")//监听指定目的地的发送消息
    public void receiveMessage(String msg){
        System.out.println("收到JMS消息：<"+msg+">");//输出获取到的消息
    }
}
