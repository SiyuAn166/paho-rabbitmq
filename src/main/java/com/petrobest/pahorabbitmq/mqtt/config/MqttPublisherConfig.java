package com.petrobest.pahorabbitmq.mqtt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@IntegrationComponentScan
public class MqttPublisherConfig {
    @Autowired
    MqttHostPropertiesConfig hostPropertiesConfig;  //mqtt服务器配置信息类
    @Autowired
    MqttPahoClientFactory factory;


    //发送消息的通道
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(hostPropertiesConfig.getClientId(), factory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(hostPropertiesConfig.getTopic());
        return messageHandler;
    }


}
