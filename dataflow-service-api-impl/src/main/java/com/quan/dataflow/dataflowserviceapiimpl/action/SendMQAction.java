package com.quan.dataflow.dataflowserviceapiimpl.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.quan.dataflow.dataflowcommon.enums.RespStatusEnum;
import com.quan.dataflow.dataflowcommon.vo.BasicResultVO;
import com.quan.dataflow.dataflowserviceapiimpl.domain.SendTaskModel;
import com.quan.dataflow.dataflowsupport.pipeline.BusinessProcess;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessContext;
import com.quan.dataflow.dataflowsupport.utils.KafkaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: SendMQAction
 * @Description: 第四步：将消息发送到MQ
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 20:10
 */

@Slf4j
public class SendMQAction implements BusinessProcess<SendTaskModel> {

    @Autowired
    private KafkaUtils kafkaUtils;

    @Value("${austin.business.topic.name}")
    private String topicName;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        // 1. 获取消息
        SendTaskModel sendTaskModel = context.getProcessModel();
        String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), SerializerFeature.WriteClassName);

        // 2. 发送消息
        try {
            kafkaUtils.send(topicName, message);
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send kafka fail! e:{},params:{}", Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));

        }
    }
}
