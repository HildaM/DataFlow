package com.quan.dataflow.dataflowhandler.handler.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.dto.model.SmsContentModel;
import com.quan.dataflow.dataflowcommon.enums.ChannelType;
import com.quan.dataflow.dataflowhandler.domain.sms.SmsParam;
import com.quan.dataflow.dataflowhandler.handler.BaseHandler;
import com.quan.dataflow.dataflowhandler.handler.Handler;
import com.quan.dataflow.dataflowhandler.script.SmsScript;
import com.quan.dataflow.dataflowsupport.dao.SmsRecordDao;
import com.quan.dataflow.dataflowsupport.domain.SmsRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: SmsHandler
 * @Description: 短信发送处理
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 19:49
 */

@Component
@Slf4j
public class SmsHandler extends BaseHandler implements Handler {

    public SmsHandler() {
        channelCode = ChannelType.SMS.getCode();
    }

    @Autowired
    private SmsRecordDao smsRecordDao;

    @Autowired
    private SmsScript smsScript;


    @Override
    public boolean handler(TaskInfo taskInfo) {
        // 1. 装备参数
        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(getSmsContent(taskInfo))
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .sendAccount(taskInfo.getSendAccount())
                .build();

        // 2. 使用接口发送信息
        try {
            List<SmsRecord> recordList = smsScript.send(smsParam);
            if (!CollUtil.isEmpty(recordList)) {
                smsRecordDao.saveAll(recordList);
            }
            return true;

        } catch (Exception e) {
            log.error("SmsHandler#handler fail:{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(smsParam));
        }
        return false;

    }


    /**
     * 如果有输入链接，则把链接拼在文案后
     * <p>
     * PS: 这里可以考虑将链接 转 短链
     * PS: 如果是营销类的短信，需考虑拼接 回TD退订 之类的文案
     */
    private String getSmsContent(TaskInfo taskInfo) {
        SmsContentModel smsContentModel = (SmsContentModel) taskInfo.getContentModel();
        if (StrUtil.isNotBlank(smsContentModel.getUrl())) {
            return smsContentModel.getContent() + " " + smsContentModel.getUrl();
        } else {
            return smsContentModel.getContent();
        }
    }
}
