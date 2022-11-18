package com.petrobest.pahorabbitmq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Autowired
    ConnectionFactory connectionFactory;

    //持久化消费队列绑定
    /*@Bean
    public Binding bindingTopicExchangeA(@Qualifier("queueA") Queue qa, @Qualifier("fanoutExc")FanoutExchange exchange) {
        return BindingBuilder.bind(qa).to(exchange);
    }*/

    //websocket消费队列绑定
    @Bean
    public Binding bindingTopicExchangeB(@Qualifier("queueB") Queue qb, @Qualifier("fanoutExc")FanoutExchange exchange) {
        return BindingBuilder.bind(qb).to(exchange);
    }

    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());  //消息发送者 json数据类型转换器
        return rabbitTemplate;
    }

    //消息接收者，将数组转化为json
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
