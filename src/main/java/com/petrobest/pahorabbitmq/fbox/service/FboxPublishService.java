package com.petrobest.pahorabbitmq.fbox.service;

import java.util.Map;

public interface FboxPublishService {
    /**
     * 控制盒子进行监控点的写入操作
     * "Version":10：主题的版本号 "name":需要修改的监控点名称 "value":需要修改监控点的值
     *
     * @param ids
     * @param dataMap 数据格式：{"Version":10, "Data":[ {"name":"Temp","value":"30"} ]}
     */
    void writeData(String[] ids, Map<String, Object> dataMap);

    /**
     * 暂停数据推送
     *
     * @param ids
     * @param enable true暂停数据推送，false恢复数据推送
     */
    void pause(String[] ids, boolean enable);

    /**
     * 重启盒子
     *
     * @param ids
     */
    void reboot(String[] ids);

    /**
     * 立即让盒子发布监控点
     *
     * @param ids
     */
    void pushDataNow(String[] ids);

    /**
     * 设置监控点条目发布周期, 作用于盒子上的所有监控点
     *
     * @param ids
     * @param secondTime 发布周期
     */
    void modifyDataPushCycle(String[] ids, String  secondTime);

    /**
     * 设置单个或多个监控点条目发布周期,如果没有使用该主题设置过的监控条目周期，
     * 会使用默认周期或者使用 MDataPubCycle 设置的周期。 当设置为 0 时，监控条目使用全局的发布周期。
     * @param ids
     * @param dataMap cycle:周期 num:该次设置的数量 item:后面是条目
     * {"cycle":10, "num":3,  "item": [ "ItemName1", "ItemName2", "ItemName3" ] } "
     */
    void batchModMonitorDataPushCycle(String[] ids, Map<String, Object> dataMap);

    /**
     * 获取盒子相关状态或信息
     * @param ids
     * @param infoType
     * infoType:
     * Taglist 获取当前条目列表
     * Topiclist 获取当前盒子的主题（订阅和可发布）
     * Pause 获取当前盒子的暂停状态（是否处于暂停）
     * MDataPubCycle 获取当前盒子监控条目的推送周期
     */
    void getInfo(String[] ids, String infoType);

}
