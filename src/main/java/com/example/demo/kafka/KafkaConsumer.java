package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: KafkaConsumer
 * @Description:
 * @Author
 * @Date 2022/3/9
 * @Version 1.0
 */
@Component
public class KafkaConsumer {

    /**
     * 监听test主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"test"})
    public void consumer(String message){
        System.out.println("test topic message : {}"+ message);
    }
}
