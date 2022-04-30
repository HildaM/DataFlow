package com.quan.dataflow.dataflowhandler.deduplication;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.quan.dataflow.dataflowcommon.constant.DataFlowConstant;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.DeduplicationType;
import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DeduplicationRuleService
 * @Description:    去重服务
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 20:04
 */

@Service
public class DeduplicationRuleService {

    public static final String DEDUPLICATION_RULE_KEY = "deduplication";

    @ApolloConfig("boss.austin")
    private Config config;

    @Autowired
    private DeduplicationHolder deduplicationHolder;


    public void duplication(TaskInfo taskInfo) {
        // 配置样例：{"deduplication_10":{"num":1,"time":300},"deduplication_20":{"num":5}}
        String deduplicationConfig = config.getProperty(
                DEDUPLICATION_RULE_KEY,
                DataFlowConstant.APOLLO_DEFAULT_VALUE_JSON_OBJECT
        );

        // 去重
        List<Integer> deduplicationList = DeduplicationType.getDeduplicationList();
        for (Integer deduplicationType : deduplicationList) {
            DeduplicationParam deduplicationParam = deduplicationHolder
                    .selectBuilder(deduplicationType)
                    .build(deduplicationConfig, taskInfo);

            if (deduplicationParam != null) {
                deduplicationHolder.selectService(deduplicationType).deduplication(deduplicationParam);
            }
        }
    }
}
