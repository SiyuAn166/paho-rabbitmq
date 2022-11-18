package com.petrobest.pahorabbitmq.mqtt.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ToString
public class MqttHostPropertiesConfig {
    @Value("${mqtt.host}")
    private String host;

    @Value("${mqtt.clientid}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.timeout}")
    private int timeout;

    @Value("${mqtt.keepalive}")
    private int keepalive;


}
