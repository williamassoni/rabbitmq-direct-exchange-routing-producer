package com.example.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("solar.default.bpm.started.direct");
    }

    @Bean
    Queue incomingQueue() {
        return QueueBuilder.durable("solar.default.bpm.started").build();
    }
    
    @Bean
    Binding binding() {
        return BindingBuilder.bind(incomingQueue()).to(exchange()).with("250");
    }
    
    @Bean
    Queue incomingQueue2() {
        return QueueBuilder.durable("solar.default.alx.started").build();
    }
    
    @Bean
    Binding binding2() {
        return BindingBuilder.bind(incomingQueue2()).to(exchange()).with("300");
    }
    
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}