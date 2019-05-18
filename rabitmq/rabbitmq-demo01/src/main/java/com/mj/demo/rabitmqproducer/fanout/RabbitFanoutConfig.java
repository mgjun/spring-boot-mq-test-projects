package com.mj.demo.rabitmqproducer.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitFanoutConfig {

    @Bean
    public Queue queuePenglei() {
        return new Queue("demo1");
    }

    @Bean
    public Queue queueSouyunku() {
        return new Queue("demo2");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingExchangeQueuePenglei() {
        return BindingBuilder.bind(queuePenglei()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingExchangeQueueSouyunku() {
        return BindingBuilder.bind(queueSouyunku()).to(fanoutExchange());
    }
}
