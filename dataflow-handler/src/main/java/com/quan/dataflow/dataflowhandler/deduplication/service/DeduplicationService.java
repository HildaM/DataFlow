package com.quan.dataflow.dataflowhandler.deduplication.service;

import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;

/**
 * @ClassName: DeduplicationService
 * @Description: 去重服务接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/29 18:03
 */
public interface DeduplicationService {

    /**
     * 去重
     * @param param
     */
    void deduplication(DeduplicationParam param);

}
