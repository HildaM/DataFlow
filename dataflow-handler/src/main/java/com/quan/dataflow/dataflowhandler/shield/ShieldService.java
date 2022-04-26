package com.quan.dataflow.dataflowhandler.shield;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;

/**
 * @ClassName: ShieldService
 * @Description: 屏蔽服务
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 20:07
 */
public interface ShieldService {

    // 屏蔽信息
    void shield(TaskInfo taskInfo);
}
