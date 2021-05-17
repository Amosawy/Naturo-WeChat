package com.awy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${netty.port}")
    private Integer port;

    //创建队列
    @Bean
    public Queue queue(){
        return new Queue("ws_queue_"+port);
    }

    //创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("ws_exchange");
    }

    //把队列绑定到交换机
    @Bean
    public Binding queueToExchange(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }
}
