package com.example.demo.example.activeMQ;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**  activemq生产者
 * Created by DJ010199 on 2020/6/12.
 */
@RestController
public class MQProducerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    /*
     * queue一对一模式，消息生产者
     */
    @RequestMapping("/sendmsg")
    public void sendmsg(@RequestParam(name = "msg") String msg) {
        // 指定消息发送的目的地及内容
        System.out.println("queue发送消息-----" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    /**
     * topic一对多模式，消息生产者
     */
    @RequestMapping("/send")
    public void send(@RequestParam(name = "msg") String msg) {
        // 指定消息发送的目的地及内容
        System.out.println("topic发送消息----" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }

}
