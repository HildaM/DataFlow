package com.quan.dataflow.dataflowhandler.deduplication.build;

import cn.hutool.core.date.DateUtil;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.AnchorState;
import com.quan.dataflow.dataflowcommon.enums.DeduplicationType;
import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: FrequencyDeduplicationBuilder
 * @Description:    一天内N次相同渠道去重 实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/29 18:17
 */

@Service
public class FrequencyDeduplicationBuilder extends AbstractDeduplicationBuilder implements Builder {
    public FrequencyDeduplicationBuilder() {
        deduplicationType = DeduplicationType.FREQUENCY.getCode();
    }

    @Override
    public DeduplicationParam build(String deduplication, TaskInfo taskInfo) {
        DeduplicationParam deduplicationParam = getParamsFromConfig(deduplicationType, deduplication, taskInfo);
        if (deduplicationParam == null) {
            return null;
        }
        deduplicationParam.setDeduplicationTime((DateUtil.endOfDay(new Date()).getTime() - DateUtil.current()) / 1000);
        deduplicationParam.setAnchorState(AnchorState.RULE_DEDUPLICATION);
        return deduplicationParam;
    }
}
