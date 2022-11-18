package com.petrobest.pahorabbitmq.runner;

import com.petrobest.pahorabbitmq.fbox.domain.BoxDO;
import com.petrobest.pahorabbitmq.fbox.service.BoxService;
import com.petrobest.pahorabbitmq.fbox.utils.FboxTopicGenerateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FboxAutoSubscriptionRunner implements ApplicationRunner {

    @Autowired
    BoxService service;
    @Autowired
    MqttPahoMessageDrivenChannelAdapter adapter;

    //springboot启动后，查询设备列表，并自动订阅所有设备消息主题
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BoxDO> list = service.list(null);
        String[] defaultFboxTopicsByList = FboxTopicGenerateUtils.getDefaultFboxTopicsByList(list);
        adapter.addTopic(defaultFboxTopicsByList);
        log.info("已自动订阅主题：");
        for (String s : defaultFboxTopicsByList) {
            log.info(s);
        }
    }
}
