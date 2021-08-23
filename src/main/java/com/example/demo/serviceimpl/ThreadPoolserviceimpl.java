package com.example.demo.serviceimpl;

import com.example.demo.example.GetController;
import com.example.demo.example.ThreadPoolExample;
import com.example.demo.service.ThreadPoolservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadPoolserviceimpl implements ThreadPoolservice {
    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolserviceimpl.class);

    @Override
    @Async
    public void sayhello(String name) {
        logger.info(name + ":Hello World!");
    }
}
