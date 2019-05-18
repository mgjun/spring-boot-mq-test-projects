package com.mj.demo.rabitmqproducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitFanoutTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void sendDemo1QueueTest() {
        String context = "此消息在，广播模式或者订阅模式队列下，有 FanoutReceiver1 FanoutReceiver2 可以收到";

        String routeKey = "demo1";

        String exchange = "fanoutExchange";

        System.out.println("sendDemo1Test : " + context);

        context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

        this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
    }

    @Test
    public void sendDemo2QueueTest() {
        String context = "此消息在，广播模式或者订阅模式队列下，有 FanoutReceiver1 FanoutReceiver2 可以收到";

        String routeKey = "demo2";

        String exchange = "fanoutExchange";

        System.out.println("sendDemo2Test : " + context);

        context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

        this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
    }

}
