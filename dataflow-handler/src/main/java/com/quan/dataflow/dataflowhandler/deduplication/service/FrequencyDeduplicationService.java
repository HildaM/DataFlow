package com.quan.dataflow.dataflowhandler.deduplication.service;

import cn.hutool.core.util.StrUtil;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.DeduplicationType;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FrequencyDeduplicationService
 * @Description: 频次去重服务
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/30 11:14
 */

@Service
public class FrequencyDeduplicationService extends AbstractDeduplicationService {

    public FrequencyDeduplicationService() {
        deduplicationType = DeduplicationType.FREQUENCY.getCode();
    }

    private static final String PREFIX = "FRE";

    /**
     * 业务规则去重 构建key
     * <p>
     * key ： receiver + templateId + sendChannel
     * <p>
     * 一天内一个用户只能收到某个渠道的消息 N 次
     *
     * @param taskInfo
     * @param receiver
     * @return
     */
    @Override
    public String deduplicationSingleKey(TaskInfo taskInfo, String receiver) {
        return PREFIX + StrUtil.C_UNDERLINE
                + receiver  + StrUtil.C_UNDERLINE
                + taskInfo.getMessageTemplateId() + StrUtil.C_UNDERLINE
                + taskInfo.getSendChannel();
    }
}
