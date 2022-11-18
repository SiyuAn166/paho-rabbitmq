package com.petrobest.pahorabbitmq.mqtt.messageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class FboxMessageHandler implements MessageHandler {

    private Logger logger = LoggerFactory.getLogger(FboxMessageHandler.class);

    private RabbitTemplate template;

    private ObjectMapper mapper;

    public FboxMessageHandler() {

    }

    public FboxMessageHandler(RabbitTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper
        ;
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        //将消息转发到rabbitmq队列
        try {
            String s = mapper.writeValueAsString(message); //转为json格式

            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON); //设定格式为application/json
            org.springframework.amqp.core.Message message1 = new org.springframework.amqp.core.Message(s.getBytes(), messageProperties);
            logger.info("FboxMessageHandler received: " + message1);
            template.send("petrobest.fanout", "1", message1);  //以json格式发送消息s
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
