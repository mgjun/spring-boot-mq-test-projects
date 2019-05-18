package com.mj.demo.rabitmqproducer.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {

    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.message.s");
    }

    @Bean
    public Queue queueYmq() {
        return new Queue("topic.ymq");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindMessage() {
        return BindingBuilder.bind(queueMessage()).to(topicExchange()).with("topic.message");
    }


    //# 0个或多个关键字匹配, * 匹配一个关键字
    @Bean
    public Binding bindMessages() {
        return BindingBuilder.bind(queueMessages()).to(topicExchange()).with("topic.message.#");
    }

    @Bean
    public Binding bindYmq() {
        return BindingBuilder.bind(queueYmq()).to(topicExchange()).with("topic.#");
    }
}
