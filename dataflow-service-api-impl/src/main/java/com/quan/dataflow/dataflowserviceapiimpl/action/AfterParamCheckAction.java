package com.quan.dataflow.dataflowserviceapiimpl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.ChannelType;
import com.quan.dataflow.dataflowcommon.enums.IdType;
import com.quan.dataflow.dataflowcommon.enums.RespStatusEnum;
import com.quan.dataflow.dataflowcommon.vo.BasicResultVO;
import com.quan.dataflow.dataflowserviceapiimpl.domain.SendTaskModel;
import com.quan.dataflow.dataflowsupport.pipeline.BusinessProcess;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: AfterParamCheckAction
 * @Description:    第三步：后置参数检查
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 19:58
 */
@Slf4j
public class AfterParamCheckAction implements BusinessProcess<SendTaskModel> {


    public static final String PHONE_REGEX_EXP = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$";

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        List<TaskInfo> taskInfo = sendTaskModel.getTaskInfo();

        // 1. 过滤掉不合法的手机号
        filterIllegalPhoneNum(taskInfo);

        // 2.

        if (CollUtil.isEmpty(taskInfo)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
        }
    }


    /**
     * @Author Hilda
     * @Description     如果指定类型是手机号的话，且渠道是发送短信，则过滤掉不合法的手机号
     * @Date 20:01 2022/4/24
     * @Param [taskInfo]
     * @returnValue void
     **/
    private void filterIllegalPhoneNum(List<TaskInfo> taskInfo) {
        // 1. 获取信息
        Integer idType = CollUtil.getFirst(taskInfo.iterator()).getIdType();
        Integer sendChannel = CollUtil.getFirst(taskInfo.iterator()).getSendChannel();

        // 2. 判断是否为指定条件
        if (IdType.PHONE.getCode().equals(idType) && ChannelType.SMS.getCode().equals(sendChannel)) {
            // 通过迭代器遍历
            Iterator<TaskInfo> iterator = taskInfo.iterator();

            // 使用正则表达式找出不合法的手机号
            while (iterator.hasNext()) {
                TaskInfo task = iterator.next();
                Set<String> illegalPhone = task.getReceiver().stream()
                        .filter(phone -> !ReUtil.isMatch(PHONE_REGEX_EXP, phone))
                        .collect(Collectors.toSet());

                if (CollUtil.isNotEmpty(illegalPhone)) {
                    task.getReceiver().removeAll(illegalPhone);
                    log.error("messageTemplateId:{} find illegal phone!{}", task.getMessageTemplateId(), JSON.toJSONString(illegalPhone));
                }

                if (CollUtil.isEmpty(task.getReceiver())) {
                    iterator.remove();
                }
            }
        }
    }


}
