package com.mj.demo.activemqcomsumer1;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQServer {

    @Value("${spring.application.name}")
    private String applicationName;

    @JmsListener(destination = "test-queue", containerFactory = "jmsQueueListener")
    public void receive(TextMessage textMessage, Session session) throws JMSException {
        try {
            System.out.println(applicationName + " == received from test-queue:" + textMessage.getText());
//            session.recover();
//            throw new RuntimeException();
        } catch (Exception e) {
            session.recover();
        }
    }

    @JmsListener(destination = "test-topic")
    public void receiveTopic(String topicMessage) {
        System.out.println(applicationName + " == received from test-topic:" + topicMessage);
    }

}
