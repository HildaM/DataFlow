package com.quan.dataflow.dataflowhandler.utils;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.ChannelType;
import com.quan.dataflow.dataflowcommon.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GroupIdMappingUtils
 * @Description:    groupId标识每个消费者组
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 19:36
 */
public class GroupIdMappingUtils {

    /**
     * 获取所有的groupIds
     * (不同的渠道不同的消息类型拥有自己的groupId)
     */
    public static List<String> getAllGroupIds() {
        List<String> groupIds = new ArrayList<>();
        for (ChannelType channelType : ChannelType.values()) {
            for (MessageType messageType : MessageType.values()) {
                groupIds.add(channelType.getCodeEn() + "." + messageType.getCodeEn());
            }
        }
        return groupIds;
    }


    /**
     * 根据TaskInfo获取当前消息的groupId
     * @param taskInfo
     * @return
     */
    public static String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        String channelCodeEn = ChannelType.getEnumByCode(taskInfo.getSendChannel()).getCodeEn();
        String msgCodeEn = MessageType.getEnumByCode(taskInfo.getMsgType()).getCodeEn();
        return channelCodeEn + "." + msgCodeEn;
    }


}
