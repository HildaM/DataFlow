package com.quan.dataflow.dataflowhandler.handler;

import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowsupport.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: BaseHandler
 * @Description:    发送各个渠道的Handler
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 17:02
 */
public class BaseHandler implements Handler {

    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;


    @Autowired
    private HandlerHolder handlerHolder;
    @Autowired
    private LogUtils logUtils;


    @Override
    public void doHandler(TaskInfo taskInfo) {

    }
}
