package com.quan.dataflow.dataflowhandler.discard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.quan.dataflow.dataflowcommon.constant.DataFlowConstant;
import com.quan.dataflow.dataflowcommon.domain.AnchorInfo;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.AnchorState;
import com.quan.dataflow.dataflowsupport.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DiscardMessageService
 * @Description: 丢弃模板服务
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 20:06
 */

@Service
public class DiscardMessageService {

    private static final String DISCARD_MESSAGE_KEY = "discard";

    @ApolloConfig("boss.austin")
    private Config config;

    @Autowired
    private LogUtils logUtils;


    /**
     * 丢弃消息，配置在apollo
     * @param taskInfo
     * @return
     */
    public boolean isDiscard(TaskInfo taskInfo) {
        // 配置示例:	["1","2"]
        JSONArray array = JSON.parseArray(config.getProperty(DISCARD_MESSAGE_KEY,
                DataFlowConstant.APOLLO_DEFAULT_VALUE_JSON_ARRAY));

        if (array.contains(String.valueOf(taskInfo.getMessageTemplateId()))) {
            logUtils.print(
                    AnchorInfo.builder()
                            .businessId(taskInfo.getBusinessId())
                            .ids(taskInfo.getReceiver())
                            .state(AnchorState.DISCARD.getCode())
                            .build()
            );
            return true;
        }
        return false;
    }
}
