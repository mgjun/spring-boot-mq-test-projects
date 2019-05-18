package com.mj.demo.rabitmqproducer.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct")
public class DirectReceiver {

    @RabbitHandler
    public void received(String message) {
        System.out.println("direct queue-> Receiver  : " + message);
    }
}
