package com.quan.dataflow.dataflowhandler.deduplication;

import com.quan.dataflow.dataflowhandler.deduplication.build.Builder;
import com.quan.dataflow.dataflowhandler.deduplication.service.DeduplicationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DeduplicationHolder
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/29 17:55
 */

@Service
public class DeduplicationHolder {

    private Map<Integer, Builder> builderHolder = new HashMap<>(4);
    private Map<Integer, DeduplicationService> serviceHolder = new HashMap<>(4);

    public Builder selectBuilder(Integer key) {
        return builderHolder.get(key);
    }

    public DeduplicationService selectService(Integer key) {
        return serviceHolder.get(key);
    }

    public void putBuilder(Integer key, Builder builder) {
        builderHolder.put(key, builder);
    }

    public void putService(Integer key, DeduplicationService service) {
        serviceHolder.put(key, service);
    }
}
