package com.quan.dataflow.dataflowserviceapiimpl.service;

import cn.monitor4all.logRecord.annotation.OperationLog;
import com.quan.dataflow.dataflowcommon.vo.BasicResultVO;
import com.quan.dataflow.dataflowserviceapi.domain.BatchSendRequest;
import com.quan.dataflow.dataflowserviceapi.domain.SendRequest;
import com.quan.dataflow.dataflowserviceapi.domain.SendResponse;
import com.quan.dataflow.dataflowserviceapi.service.SendService;
import com.quan.dataflow.dataflowserviceapiimpl.domain.SendTaskModel;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessContext;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @ClassName: SendServiceImpl
 * @Description: 发送消息服务实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 15:02
 */

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private ProcessController processController;

    @Override
    @OperationLog(bizType = "SendService#send", bizId = "#sendRequest.messageTemplateId", msg = "#sendRequest")
    public SendResponse send(SendRequest sendRequest) {

        // 1. 装配发送信息的Model
        // @Builder：建造者模式创建
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        // 2. 配置责任链上下文
        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success())
                .build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        return null;
    }
}
