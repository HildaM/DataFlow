package com.quan.dataflow.dataflowserviceapi.service;

import com.quan.dataflow.dataflowserviceapi.domain.BatchSendRequest;
import com.quan.dataflow.dataflowserviceapi.domain.SendRequest;
import com.quan.dataflow.dataflowserviceapi.domain.SendResponse;

/**
 * @ClassName: SendService
 * @Description: 消息发送服务接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 14:37
 */


public interface SendService {

    /**
     * 单文案发送接口
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);


    /**
     * 多文案发送接口
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
