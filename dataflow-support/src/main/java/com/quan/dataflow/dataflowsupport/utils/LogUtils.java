package com.quan.dataflow.dataflowsupport.utils;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.CustomLogListener;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.quan.dataflow.dataflowcommon.domain.AnchorInfo;
import com.quan.dataflow.dataflowcommon.domain.LogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogUtils
 * @Description: 日志配置类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 17:06
 */

@Slf4j
@Component
public class LogUtils extends CustomLogListener {

    @Autowired
    private KafkaUtils kafkaUtils;

    @Value("${austin.business.log.topic.name}")
    private String topicName;


    @Override
    public void createLog(LogDTO logDTO) throws Exception {
        log.info(JSON.toJSONString(logDTO));
    }

    /**
     * 记录当前对象信息
     */
    public void print(LogParam logParam) {
        logParam.setTimestamp(System.currentTimeMillis());
        log.info(JSON.toJSONString(logParam));
    }


    /**
     * 记录打点信息
     */
    public void print(AnchorInfo anchorInfo) {
        anchorInfo.setTimestamp(System.currentTimeMillis());
        String message = JSON.toJSONString(anchorInfo);
        log.info(message);

        try {
            kafkaUtils.send(topicName, message);
        } catch (Exception e) {
            log.error("LogUtils#print kafka fail! e:{},params:{}", Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(anchorInfo));
        }
    }


    /**
     * 记录当前对象信息和打点信息
     */
    public void print(LogParam logParam, AnchorInfo anchorInfo) {
        print(anchorInfo);
        print(logParam);
    }
}
