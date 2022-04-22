package com.quan.dataflow.dataflowserviceapiimpl.config;

import com.quan.dataflow.dataflowsupport.pipeline.BusinessProcess;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @ClassName: PipelineConfig
 * @Description:    发送信息的配置类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 16:53
 */

@Configuration
public class PipelineConfig {

    /**
     * 普通发送执行流程
     * 1. 前置参数校验
     * 2. 组装参数
     * 3. 后置参数校验
     * 4. 发送消息至MQ
     * @return
     */
    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        ArrayList<BusinessProcess> processList = new ArrayList<>();

        // TODO 配置

        return processTemplate;
    }
}
