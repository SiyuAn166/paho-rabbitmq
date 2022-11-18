package com.petrobest.pahorabbitmq.fbox.controller;

import com.petrobest.pahorabbitmq.common.response.ResponseData;
import com.petrobest.pahorabbitmq.fbox.utils.FboxTopicGenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/fbox/topic")
public class TopicController {

    /*@Autowired
    private MqttMessageGateway gateway;*/

    @Autowired
    @Qualifier("blankMqttMessageAdapter")
    MqttPahoMessageDrivenChannelAdapter adapter;

    /*@RequestMapping("/send/{topic}/{data}")
    public ResponseData send(@PathVariable("topic") String topic, @PathVariable("data") String data) {

        gateway.sendToMqtt(data, topic);
        return ResponseData.ok();
    }*/


    @RequestMapping(value = "/subscribe", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData sub(String[] ids) {
        try {
            String[] topic = FboxTopicGenerateUtils.getDefaultFboxTopicsByIDs(ids);
            adapter.addTopic(topic);
            return ResponseData.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("订阅失败");
        }
    }

    @RequestMapping(value = "/unsubscribe", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData unsub(String[] ids) {
        try {
            String[] topic = FboxTopicGenerateUtils.getDefaultFboxTopicsByIDs(ids);
            adapter.removeTopic(topic);
            return ResponseData.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("取消订阅失败");
        }
    }

    @RequestMapping(value = "/getTopicList")
    public ResponseData getTopicList() {
        String[] topics = adapter.getTopic();
        HashMap<String, Object> map = new HashMap<>();
        map.put("topics", topics);
        return ResponseData.ok(map);
    }
}
