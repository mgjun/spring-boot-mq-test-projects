package com.mj.demo.rabitmqproducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitDirectTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendHelloTest() {
        String context = "此消息在，默认的交换机模式队列下，有 helloReceiver 可以收到";

        String routeKey = "hello";

        context = "routeKey:" + routeKey + ",context:" + context;

        System.out.println("sendHelloTest : " + context);

        this.rabbitTemplate.convertAndSend(routeKey, context);
    }


    @Test
    public void sendDirectTest() {
        String context = "此消息在，默认的交换机模式队列下，有 helloReceiver 可以收到";

        String routeKey = "direct";

        context = "routeKey:" + routeKey + ",context:" + context;

        System.out.println("sendDirectTest : " + context);

        this.rabbitTemplate.convertAndSend(routeKey, context);
    }

}
