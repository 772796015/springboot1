package com.example.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jms.Queue;
import javax.jms.Topic;
/** Activemq 配置类
 * Created by DJ010199 on 2020/6/12.
 */
@Configuration
public class ActiveMQConfiguration {


    //创建两个queue队列 start
    @Bean(name="telQueue")
    public Queue telQueue() {
        return new ActiveMQQueue("telQueue");
    }

    @Bean(name = "emailQueue")
    public Queue emailQueue() {
        return new ActiveMQQueue("emailQueue");
    }
    //创建两个queue队列 end



    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sample.topic");

    }

}
