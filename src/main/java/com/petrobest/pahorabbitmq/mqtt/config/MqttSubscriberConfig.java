package com.petrobest.pahorabbitmq.mqtt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petrobest.pahorabbitmq.mqtt.messageHandler.FboxMessageHandler;
import com.petrobest.pahorabbitmq.mqtt.utils.MqttPahoMessageDrivenChannelAdapterUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan
public class MqttSubscriberConfig {

    @Autowired
    MqttPahoMessageDrivenChannelAdapterUtils utils;


    @Autowired
    RabbitTemplate template;

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
    //接受消息通道
    @Bean
    public MessageChannel mqttInputChannel() {
        DirectChannel channel = new DirectChannel();
        channel.subscribe(new FboxMessageHandler(template,mapper()));
        return channel;

    }

    /*@Bean(name = "mqttMessageAdapter")
    public MqttPahoMessageDrivenChannelAdapter mqttMessageAdapter() {
        MqttPahoMessageDrivenChannelAdapter adapter = utils.getSpecifiedIDMqttPahoMessageDrivenChannelAdapter(mqttInputChannel(),"300218020188");
        return adapter;
    }*/

    @Bean(name = "blankMqttMessageAdapter")
    public MqttPahoMessageDrivenChannelAdapter blankMqttMessageAdapter() {
        MqttPahoMessageDrivenChannelAdapter adapter = utils.getBlankMqttPahoMessageDrivenChannelAdapter(mqttInputChannel());
        return adapter;
    }


}


