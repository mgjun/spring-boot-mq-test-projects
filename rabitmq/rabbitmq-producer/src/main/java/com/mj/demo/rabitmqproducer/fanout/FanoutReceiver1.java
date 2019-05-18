package com.mj.demo.rabitmqproducer.fanout;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "demo1")
public class FanoutReceiver1 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("接收者 FanoutReceiver demo1," + message);
    }
}
