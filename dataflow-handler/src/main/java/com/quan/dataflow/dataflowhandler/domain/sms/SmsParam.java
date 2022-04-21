package com.quan.dataflow.dataflowhandler.domain.sms;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @ClassName: SmsParam
 * @Description:    发送短信参数
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/21 20:37
 */
@Data
@Builder
public class SmsParam {

    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;

    /**
     * 发送账号
     */
    private Integer sendAccount;

}
