package com.petrobest.pahorabbitmq.fbox.controller;

import com.petrobest.pahorabbitmq.common.response.ResponseData;
import com.petrobest.pahorabbitmq.fbox.service.FboxPublishService;
import com.petrobest.pahorabbitmq.mqtt.service.MqttMessageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fbox/publish")
public class FboxPublishController {

    @Autowired
    private MqttMessageGateway gateway;

    @Autowired
    private FboxPublishService service;


    @RequestMapping(value = "/writeData", method = RequestMethod.POST)
    public ResponseData writeData(String[] ids,  Map<String, Object> dataMap) {
        service.writeData(ids, dataMap);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    public ResponseData pause(@RequestParam("ids") String[] ids,  @RequestParam("enable") Boolean enable) {
        service.pause(ids, enable);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/reboot", method = RequestMethod.POST)
    public ResponseData reboot( String[] ids) {
        service.reboot(ids);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/pushDataNow", method = RequestMethod.POST)
    public ResponseData pushDataNow(String[] ids) {
        service.pushDataNow(ids);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/modifyDataPushCycle", method = RequestMethod.POST)
    public ResponseData modifyDataPushCycle(String[] ids, String secondTime) {
        service.modifyDataPushCycle(ids, secondTime);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/batchModMonitorDataPushCycle", method = RequestMethod.POST)
    public ResponseData batchModMonitorDataPushCycle( String[] ids,  Map<String, Object> dataMap) {
        service.batchModMonitorDataPushCycle(ids, dataMap);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public ResponseData getInfo( String[] ids,  String infoType) {
        service.getInfo(ids, infoType);
        return ResponseData.ok();
    }
}
