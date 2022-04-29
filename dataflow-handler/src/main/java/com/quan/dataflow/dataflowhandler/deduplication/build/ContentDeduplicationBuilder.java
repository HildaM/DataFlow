package com.quan.dataflow.dataflowhandler.deduplication.build;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.AnchorState;
import com.quan.dataflow.dataflowcommon.enums.DeduplicationType;
import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ContentDeduplicationBuilder
 * @Description:    N分钟相同内容去重 实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/29 18:13
 */

@Service
public class ContentDeduplicationBuilder extends AbstractDeduplicationBuilder implements Builder {

    public ContentDeduplicationBuilder() {
        deduplicationType = DeduplicationType.CONTENT.getCode();
    }

    @Override
    public DeduplicationParam build(String deduplication, TaskInfo taskInfo) {
        DeduplicationParam deduplicationParam = getParamsFromConfig(deduplicationType, deduplication, taskInfo);
        if (deduplicationParam == null) {
            return null;
        }
        deduplicationParam.setAnchorState(AnchorState.CONTENT_DEDUPLICATION);
        return deduplicationParam;
    }
}
