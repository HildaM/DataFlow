package com.quan.dataflow.dataflowhandler.deduplication.build;

import com.alibaba.fastjson.JSONObject;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowhandler.deduplication.DeduplicationHolder;
import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @ClassName: AbstractDeduplicationBuilder
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/29 18:11
 */
public abstract class AbstractDeduplicationBuilder implements Builder {

    protected Integer deduplicationType;

    @Autowired
    private DeduplicationHolder deduplicationHolder;

    @PostConstruct
    public void init() {
        deduplicationHolder.putBuilder(deduplicationType, this);
    }

    public DeduplicationParam getParamsFromConfig(Integer key, String duplicationConfig, TaskInfo taskInfo) {
        JSONObject object = JSONObject.parseObject(duplicationConfig);
        if (object == null) {
            return null;
        }
        DeduplicationParam deduplicationParam = JSONObject.parseObject(object.getString(DEDUPLICATION_CONFIG_PRE + key), DeduplicationParam.class);
        if (deduplicationParam == null) {
            return null;
        }
        deduplicationParam.setTaskInfo(taskInfo);
        return deduplicationParam;
    }
}
