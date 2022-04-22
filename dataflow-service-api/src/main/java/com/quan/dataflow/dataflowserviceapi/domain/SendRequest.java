package com.quan.dataflow.dataflowserviceapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: SendRequest
 * @Description: 发送接口的参数
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 14:37
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder // @Builder注释为你的类生成相对略微复杂的构建器API。
public class SendRequest {

    /**
     * 执行业务类型(默认填写 "send")
     */
    private String code;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     */
    private MessageParam messageParam;
}
