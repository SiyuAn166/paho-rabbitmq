package com.petrobest.pahorabbitmq.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    /*@Bean(name = "queueA")
    public Queue getQueueA() {
        return new Queue("persistence-queue");
    }*/
    @Bean(name = "queueB")
    public Queue getQueueB() {

        return QueueBuilder.durable("websocket-queue").withArgument("x-message-ttl", 10000).build(); //设置过期时间10秒
    }
}
