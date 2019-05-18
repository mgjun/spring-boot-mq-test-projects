package com.mj.demo.activemqconsumer02;

import javax.jms.ConnectionFactory;
import javax.jms.QueueConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean("jmsQueueListener")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        final DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setTransactionManager(transactionManager());
        factory.setSessionAcknowledgeMode(2);
        return factory;

    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(jmsConnectionFactory());
        return jmsTransactionManager;
    }

    @Bean
    public QueueConnectionFactory jmsConnectionFactory() {
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(QueueConnectionFactory queueConnectionFactory){
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setDeliveryMode(2);//进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setConnectionFactory(queueConnectionFactory);
        jmsTemplate.setSessionAcknowledgeMode(2);//客户端签收模式
        return jmsTemplate;
    }

    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次   这里设置为10次
        redeliveryPolicy.setMaximumRedeliveries(10);
        //重发时间间隔,默认为1秒
        redeliveryPolicy.setInitialRedeliveryDelay(1);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;
    }

}
