package com.petrobest.pahorabbitmq.rabbitmq.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {

    /*@Bean(name = "topicExchangeA")
    public TopicExchange getTopicExchange() {
        return new TopicExchange("topicExchangeA");
    }*/

    @Bean(name = "fanoutExc")
    public FanoutExchange getFanoutExchange() {

        return new FanoutExchange("petrobest.fanout");
    }
}
