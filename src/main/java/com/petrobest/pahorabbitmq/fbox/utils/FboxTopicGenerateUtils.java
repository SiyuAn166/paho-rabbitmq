package com.petrobest.pahorabbitmq.fbox.utils;

import com.petrobest.pahorabbitmq.fbox.domain.BoxDO;
import com.petrobest.pahorabbitmq.fbox.exception.TopicGenerateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FboxTopicGenerateUtils {

    public static String generateFboxTopic(String... args) {
        if (args == null || args.length == 0) {
            return "";
        }

        StringBuffer topicBuffer = new StringBuffer();
        for (String arg : args) {
            if (arg != null && !"".equals(arg)) {
                topicBuffer.append(arg);
                if ("#".equals(arg)) {
                    break;
                }
            }
        }
        return topicBuffer.toString();

    }

    /**
     * 根据id批量生成默认主题
     *
     * @param ids
     * @return
     */
    public static String[] getDefaultFboxTopicsByIDs(String... ids) throws TopicGenerateException {
        if (ids == null || ids.length == 0) {
            throw new TopicGenerateException("传入的id数组为空");
        }
        String prefix = FboxPropertiesUtils.getProperty("topic_prefix");
        String suffix = FboxPropertiesUtils.getProperty("topic_suffix");

        StringBuffer sb = new StringBuffer();
        Set<String> topicSet = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null && !"".equals(ids[i])) {
                sb.append(prefix).append(ids[i]).append(suffix).append("+"); //订阅此id的所有主题
                topicSet.add(sb.toString());
                sb.setLength(0); //清空缓冲区
            }
        }

        return topicSet.toArray(new String[topicSet.size()]);
    }

    /**
     * 根据list集合，批量生成默认主题
     * @param list
     * @return
     * @throws TopicGenerateException
     */
    public static String[] getDefaultFboxTopicsByList(List<BoxDO> list) throws TopicGenerateException{
        if (list == null || list.size() <= 0) {
            throw new TopicGenerateException("传入的设备集合为空");
        }

        String[] ids = new String[list.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = list.get(i).getBoxDeviceId();
        }

        return getDefaultFboxTopicsByIDs(ids);
    }

    /**
     * 根据id订阅指定主题 topic
     * @param topic
     * @param ids
     * @return
     * @throws TopicGenerateException
     */
    public static String[] getSpecifiedFboxTopicsByIDs(String topic, String... ids) throws TopicGenerateException {
        if (ids == null || ids.length == 0) {
            throw new TopicGenerateException("传入的id数组为空");
        }
        String prefix = FboxPropertiesUtils.getProperty("topic_prefix");
        String suffix = FboxPropertiesUtils.getProperty("topic_suffix");

        StringBuffer sb = new StringBuffer();
        Set<String> topicSet = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null && !"".equals(ids[i])) {
                sb.append(prefix).append(ids[i]).append(suffix).append(topic); //订阅此id的topic主题
                topicSet.add(sb.toString());
                sb.setLength(0); //清空缓冲区
            }
        }

        return topicSet.toArray(new String[topicSet.size()]);
    }
}

