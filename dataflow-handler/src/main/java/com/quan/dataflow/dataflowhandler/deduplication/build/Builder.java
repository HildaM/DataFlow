package com.quan.dataflow.dataflowhandler.deduplication.build;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;

/**
 * @ClassName: Builder
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/29 18:00
 */
public interface Builder {

    String DEDUPLICATION_CONFIG_PRE = "deduplication_";

    /**
     * 根据配置构建去重参数
     *
     * @param deduplication
     * @param taskInfo
     * @return
     */
    DeduplicationParam build(String deduplication, TaskInfo taskInfo);

}
