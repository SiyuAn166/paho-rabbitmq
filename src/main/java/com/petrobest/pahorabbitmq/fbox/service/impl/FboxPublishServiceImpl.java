package com.petrobest.pahorabbitmq.fbox.service.impl;

import com.alibaba.fastjson.JSON;
import com.petrobest.pahorabbitmq.fbox.exception.FboxPublishExecuteException;
import com.petrobest.pahorabbitmq.fbox.service.FboxPublishService;
import com.petrobest.pahorabbitmq.fbox.utils.FboxTopicGenerateUtils;
import com.petrobest.pahorabbitmq.mqtt.service.MqttMessageGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FboxPublishServiceImpl implements FboxPublishService {

    private static final String TOPIC_WRITE_DATA = "WriteData";
    private static final String TOPIC_PAUSE = "Pause";
    private static final String TOPIC_REBOOT = "Reboot";
    private static final String TOPIC_PUSH_DATA_NOW = "MDataPubNow";
    private static final String TOPIC_MODIFY_DATA_PUSH_CYCLE = "MDataPubCycle";
    private static final String TOPIC_BATCH_MOD_MONITORDATA_PUSH_CYCLE = "MDPCS";
    private static final String TOPIC_GET_INFO = "GetInfo";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MqttMessageGateway gateway;

    @Override
    public void writeData(String[] ids, Map<String, Object> dataMap) {
        try {
            execute(TOPIC_WRITE_DATA, ids, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void pause(String[] ids, boolean enable) {
        String type = enable ? "Enable" : "Disable";
        try {
            execute(TOPIC_PAUSE, ids, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void reboot(String[] ids) {
        try {
            execute(TOPIC_REBOOT, ids, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pushDataNow(String[] ids) {

        try {
            execute(TOPIC_PUSH_DATA_NOW, ids, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyDataPushCycle(String[] ids, String secondTime) {
        try {
            execute(TOPIC_MODIFY_DATA_PUSH_CYCLE, ids, secondTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchModMonitorDataPushCycle(String[] ids, Map<String, Object> dataMap) {
        try {
            execute(TOPIC_BATCH_MOD_MONITORDATA_PUSH_CYCLE, ids, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getInfo(String[] ids, String infoType) {
        try {
            execute(TOPIC_GET_INFO, ids, infoType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String specifiedTopic, String[] ids, Object data) throws Exception {
        String[] topics = FboxTopicGenerateUtils.getSpecifiedFboxTopicsByIDs(specifiedTopic, ids);
        if (data == null) {
            doSend(topics, "");
        } else if (data instanceof String) {
            doSend(topics, (String) data);
        } else if (data instanceof Map) {
            String dataMap = JSON.toJSONString(data);
            doSend(topics, dataMap);
        } else {
            throw new FboxPublishExecuteException("执行失败！数据类型不是String、Map、null。");
        }
    }

    private void doSend(String[] topics, String data) {
        for (String topic : topics) {
            logger.info(topic);
            gateway.sendToMqtt(data, topic);
        }

    }
}
