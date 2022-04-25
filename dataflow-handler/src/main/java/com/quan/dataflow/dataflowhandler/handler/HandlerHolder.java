package com.quan.dataflow.dataflowhandler.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HandlerHolder
 * @Description: 发送渠道channel ---> Handler 的映射
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 17:04
 */

@Component
public class HandlerHolder {
    private Map<Integer, Handler> handlers = new HashMap<>(128);

    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }

}
