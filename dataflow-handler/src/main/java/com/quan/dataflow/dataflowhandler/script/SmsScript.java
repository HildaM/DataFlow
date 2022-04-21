package com.quan.dataflow.dataflowhandler.script;

import com.quan.dataflow.dataflowhandler.domain.sms.SmsParam;
import com.quan.dataflow.dataflowsupport.domain.SmsRecord;

import java.util.List;

/**
 * @ClassName: SmsScript
 * @Description: 短信脚本接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/21 19:17
 */
public interface SmsScript {

    /**
     * 发送短信
     * @param smsParam
     * @return 渠道商接口返回值
     * @throws Exception
     */
    List<SmsRecord> send(SmsParam smsParam) throws Exception;

}