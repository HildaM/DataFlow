package com.quan.dataflow.dataflowserviceapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: BatchSendRequest
 * @Description:    群发消息的请求类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 14:42
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchSendRequest {

    /**
     * 执行业务类型
     * 必传,参考 BusinessCode枚举
     */
    private String code;


    /**
     * 消息模板Id
     * 必传
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     * 必传
     */
    private List<MessageParam> messageParamList;
}
