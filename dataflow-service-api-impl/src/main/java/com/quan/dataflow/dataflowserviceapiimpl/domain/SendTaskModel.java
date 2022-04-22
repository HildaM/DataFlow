package com.quan.dataflow.dataflowserviceapiimpl.domain;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowserviceapi.domain.MessageParam;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: SendTaskModel
 * @Description: 发送消息任务模型
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 15:27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;
}
