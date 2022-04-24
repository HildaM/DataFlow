package com.quan.dataflow.dataflowserviceapiimpl.config;

import com.quan.dataflow.dataflowserviceapi.enums.BusinessCode;
import com.quan.dataflow.dataflowserviceapiimpl.action.AfterParamCheckAction;
import com.quan.dataflow.dataflowserviceapiimpl.action.AssembleAction;
import com.quan.dataflow.dataflowserviceapiimpl.action.PreParamCheckAction;
import com.quan.dataflow.dataflowserviceapiimpl.action.SendMQAction;
import com.quan.dataflow.dataflowsupport.pipeline.BusinessProcess;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessController;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        processList.add(preParamCheckAction());
        processList.add(assembleAction());
        processList.add(afterParamCheckAction());
        processList.add(sendMqAction());

        processTemplate.setProcessList(processList);
        return processTemplate;
    }


    /**
     * @Author Hilda
     * @Description     装配ProcessController
     * @Date 20:24 2022/4/24
     * @Param []
     * @returnValue com.quan.dataflow.dataflowsupport.pipeline.BusinessProcess
     **/
    @Bean
    public ProcessController processController() {
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }



    private BusinessProcess sendMqAction() {
        return new SendMQAction();
    }

    private BusinessProcess afterParamCheckAction() {
        return new AfterParamCheckAction();
    }

    private BusinessProcess assembleAction() {
        return new AssembleAction();
    }

    private BusinessProcess preParamCheckAction() {
        return new PreParamCheckAction();
    }


}
