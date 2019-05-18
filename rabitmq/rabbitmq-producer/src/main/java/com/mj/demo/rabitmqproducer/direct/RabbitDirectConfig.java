package com.mj.demo.rabitmqproducer.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("direct");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingExchangeDirectQueue(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct");
    }


}
