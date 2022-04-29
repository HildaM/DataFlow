package com.quan.dataflow.dataflowhandler.handler;

import com.quan.dataflow.dataflowcommon.domain.AnchorInfo;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.AnchorState;
import com.quan.dataflow.dataflowsupport.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @ClassName: BaseHandler
 * @Description:    发送各个渠道的Handler
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 17:02
 */
public abstract class BaseHandler implements Handler {

    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;

    @Autowired
    private HandlerHolder handlerHolder;

    @Autowired
    private LogUtils logUtils;


    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode, this);
    }


    @Override
    public void doHandler(TaskInfo taskInfo) {
        if (handler(taskInfo)) {
            logUtils.print(
                    AnchorInfo.builder()
                            .state(AnchorState.SEND_SUCCESS.getCode())
                            .businessId(taskInfo.getBusinessId())
                            .ids(taskInfo.getReceiver()).build());
            return;
        }
        logUtils.print(
                AnchorInfo.builder()
                        .state(AnchorState.SEND_FAIL.getCode())
                        .businessId(taskInfo.getBusinessId())
                        .ids(taskInfo.getReceiver()).build());
    }


    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);

}
