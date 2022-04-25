package com.quan.dataflow.dataflowserviceapiimpl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.quan.dataflow.dataflowcommon.enums.RespStatusEnum;
import com.quan.dataflow.dataflowcommon.vo.BasicResultVO;
import com.quan.dataflow.dataflowserviceapi.domain.MessageParam;
import com.quan.dataflow.dataflowserviceapiimpl.domain.SendTaskModel;
import com.quan.dataflow.dataflowsupport.pipeline.BusinessProcess;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: PreParamCheckAction
 * @Description: 第一步：事前参数检查
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 18:24
 */

@Slf4j
public class PreParamCheckAction implements BusinessProcess<SendTaskModel> {
    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        // 1. 获取context
        SendTaskModel sendTaskModel = context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();

        // 2. 验证是否为空
        if (messageTemplateId == null || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 3. 过滤掉不符合的参数
        // !StrUtil.isBlank(messageParam.getReceiver())：选择不为空的参数！
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(messageParam -> !StrUtil.isBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(resultMessageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 4. 封装过滤好的参数
        sendTaskModel.setMessageParamList(resultMessageParamList);
    }
}
