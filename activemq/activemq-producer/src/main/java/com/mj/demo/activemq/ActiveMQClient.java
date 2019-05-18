package com.mj.demo.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQClient {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMQClient(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String message) {
        jmsTemplate.convertAndSend(new ActiveMQQueue("test-queue"), "demo-queue -> " + message);
    }

    public void sendTopic(String message) {
        jmsTemplate.convertAndSend(new ActiveMQTopic("test-topic"),"demo-topic -> " + message);
    }
}
