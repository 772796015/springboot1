package com.example.demo;

import com.example.demo.kafka.KafkaSender;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement  //开启事务
@MapperScan(value = "com.example.demo.mapper")
@EnableScheduling
public class Springboot1Application {
	@Autowired
	private KafkaSender kafkaSender;

	//然后每隔1分钟执行一次
	@Scheduled(fixedRate = 1000 * 1)
	public void testKafka() throws Exception {
		kafkaSender.sendTest();
	}

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}



}
