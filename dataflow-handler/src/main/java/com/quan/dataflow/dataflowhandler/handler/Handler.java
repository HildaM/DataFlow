package com.quan.dataflow.dataflowhandler.handler;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;

/**
 * @ClassName: Handler
 * @Description: 消息处理器
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 17:01
 */
public interface Handler {

    /**
     * 处理器
     */
    void doHandler(TaskInfo taskInfo);

}
