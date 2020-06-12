package com.example.demo.example.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/**   activemq消费者
 * Created by DJ010199 on 2020/6/12.
 */
@RestController
public class MQConsumerController {
    /*
	 * queue模式监听和读取消息
	 */
    @JmsListener(destination="sample.queue")
    public void readActiveQueue(String message) {
        System.out.println("接受到：" + message);
        //TODO something
    }



    /*
        * topic模式监听和读取消息1
        */
    @JmsListener(destination="sample.topic")
    public void readActiveTopic1(String message) {
        System.out.println("topic1模式接受到：" + message);
        //TODO something
    }

    /*
        * topic模式监听和读取消息2
        */
    @JmsListener(destination="sample.topic")
    public void readActiveTopic2(String message) {
        System.out.println("topic2模式接受到：" + message);
        //TODO something
    }
}
