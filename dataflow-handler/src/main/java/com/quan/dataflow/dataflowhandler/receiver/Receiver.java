package com.quan.dataflow.dataflowhandler.receiver;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.quan.dataflow.dataflowcommon.domain.AnchorInfo;
import com.quan.dataflow.dataflowcommon.domain.LogParam;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.AnchorState;
import com.quan.dataflow.dataflowhandler.pending.Task;
import com.quan.dataflow.dataflowhandler.pending.TaskPendingHolder;
import com.quan.dataflow.dataflowhandler.utils.GroupIdMappingUtils;
import com.quan.dataflow.dataflowsupport.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: Receiver
 * @Description: 消费MQ的信息
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 19:35
 */

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  // 原型模式，每次注入Bean的时候创建新的实例
public class Receiver {

    private static final String LOG_BIZ_TYPE = "Receiver#consumer";
    @Autowired
    private ApplicationContext context;

    @Autowired
    private TaskPendingHolder taskPendingHolder;

    @Autowired
    private LogUtils logUtils;


    @KafkaListener(topics = "#{'${austin.business.topic.name}'}")
    public void consumer(ConsumerRecord<?, String> consumerRecord, @Header(KafkaHeaders.GROUP_ID) String topicGroupId) {
        Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());

        if (kafkaMessage.isPresent()) {
            // 1. 将kafkaMessage的JSON信息转换为TaskInfo的类
            List<TaskInfo> taskInfoList = JSON.parseArray(kafkaMessage.get(), TaskInfo.class);

            // 2. 获取对应的messageGroupId
            String messageGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(CollUtil.getFirst(taskInfoList.iterator()));

            // 3. 每个消费者组只消费它们关心的信息
            if (topicGroupId.equals(messageGroupId)) {
                for (TaskInfo taskInfo : taskInfoList) {
                    logUtils.print(
                            LogParam.builder()
                                    .bizType(LOG_BIZ_TYPE)
                                    .object(taskInfo)
                                    .build(),
                            AnchorInfo.builder()
                                    .ids(taskInfo.getReceiver())
                                    .businessId(taskInfo.getBusinessId())
                                    .state(AnchorState.RECEIVE.getCode())
                                    .build()
                    );

                    // 通过ApplicationContext的getBean方法来获取Spring容器中已初始化的bean
                    Task task = context.getBean(Task.class).setTaskInfo(taskInfo);

                    taskPendingHolder.route(topicGroupId).execute(task);
                }
            }
        }
    }

}
