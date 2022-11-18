package com.petrobest.pahorabbitmq.mqtt.service;

import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@IntegrationComponentScan
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {

    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

}
