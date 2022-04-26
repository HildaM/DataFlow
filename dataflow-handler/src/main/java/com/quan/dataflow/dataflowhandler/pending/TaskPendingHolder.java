package com.quan.dataflow.dataflowhandler.pending;

import org.springframework.stereotype.Component;

import javax.persistence.StoredProcedureQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName: TaskPendingHolder
 * @Description:    存储 每种消息类型 与 TaskPending 的关系
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 20:10
 */

@Component
public class TaskPendingHolder {

    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }
}
