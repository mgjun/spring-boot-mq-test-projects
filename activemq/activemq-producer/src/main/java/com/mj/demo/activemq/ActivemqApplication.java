package com.mj.demo.activemq;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class ActivemqApplication {

    @Autowired
    private ActiveMQClient activeMQClient;

    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("start to send message");
        activeMQClient.send("hello world");
        activeMQClient.sendTopic("hello world-topic");
        stopWatch.stop();
        System.out.println("total cost seconds: " + stopWatch.getTotalTimeSeconds());
    }

    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }

}
