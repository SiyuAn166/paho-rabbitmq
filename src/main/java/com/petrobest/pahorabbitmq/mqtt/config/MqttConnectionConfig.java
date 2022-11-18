package com.petrobest.pahorabbitmq.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Configuration
public class MqttConnectionConfig {

    @Autowired
    MqttHostPropertiesConfig hostPropertiesConfig;

    /**
     * MQTT 服务器连接配置
     *
     * @return
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(hostPropertiesConfig.getUsername());
        mqttConnectOptions.setPassword(hostPropertiesConfig.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostPropertiesConfig.getHost()});
        mqttConnectOptions.setKeepAliveInterval(hostPropertiesConfig.getKeepalive());
        return mqttConnectOptions;
    }

    /**
     * MQTT 客户端
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

}
