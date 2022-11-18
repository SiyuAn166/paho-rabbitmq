package com.petrobest.pahorabbitmq.mqtt.utils;

import com.petrobest.pahorabbitmq.fbox.config.FboxPropertiesConfig;
import com.petrobest.pahorabbitmq.fbox.utils.FboxTopicGenerateUtils;
import com.petrobest.pahorabbitmq.mqtt.config.MqttHostPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class MqttPahoMessageDrivenChannelAdapterUtils {
    @Autowired
    MqttHostPropertiesConfig hostPropertiesConfig;  //mqtt服务器配置信息类

    @Autowired
    MqttPahoClientFactory factory;

    @Autowired
    FboxPropertiesConfig fboxPropertiesConfig;


    /**
     * 订阅所有盒子的所有主题
     *
     * @param messageChannel
     * @return
     *//*
    public MqttPahoMessageDrivenChannelAdapter getSimpleMqttPahoMessageDrivenChannelAdapter(MessageChannel messageChannel) {
        String topic = FboxTopicGenerateUtils.generateFboxTopic(fboxPropertiesConfig.getTopicPrefix(), "+", fboxPropertiesConfig.getTopicSuffix(), "+");
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(hostPropertiesConfig.getClientId() + "_inbound", factory, topic);
        adapter.setCompletionTimeout(hostPropertiesConfig.getTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(messageChannel);

        return adapter;
    }


    *//**
     * 根据盒子ID订阅该盒子的所有主题
     *
     * @param messageChannel
     * @param fboxID
     * @return
     */
    public MqttPahoMessageDrivenChannelAdapter getSpecifiedIDMqttPahoMessageDrivenChannelAdapter(MessageChannel messageChannel, String fboxID) {

        String topic = FboxTopicGenerateUtils.generateFboxTopic(fboxPropertiesConfig.getTopicPrefix(), fboxID, fboxPropertiesConfig.getTopicSuffix(), "+");

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(hostPropertiesConfig.getClientId() + "_inbound", factory, topic);
        adapter.setCompletionTimeout(hostPropertiesConfig.getTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(messageChannel);

        return adapter;
    }

    /**
     * 创建订阅""主题的适配器
     *
     * @param messageChannel
     * @return
     */
    public MqttPahoMessageDrivenChannelAdapter getBlankMqttPahoMessageDrivenChannelAdapter(MessageChannel messageChannel) {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(hostPropertiesConfig.getClientId() + "_inbound", factory, hostPropertiesConfig.getTopic());
        adapter.setCompletionTimeout(hostPropertiesConfig.getTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(messageChannel);

        return adapter;
    }

}
