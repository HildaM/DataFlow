package com.quan.dataflow.dataflowsupport.exception;

import com.quan.dataflow.dataflowcommon.enums.RespStatusEnum;
import com.quan.dataflow.dataflowsupport.pipeline.ProcessContext;

/**
 * @ClassName: ProcessException
 * @Description: 自定义异常处理
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 16:34
 */
public class ProcessException extends RuntimeException{

    /**
     * 流程处理上下文
     */
    private final ProcessContext processContext;

    public ProcessException(ProcessContext processContext) {
        super();
        this.processContext = processContext;
    }

    public ProcessException(ProcessContext processContext, Throwable cause) {
        super(cause);
        this.processContext = processContext;
    }

    @Override
    public String getMessage() {
        if (this.processContext != null) {
            return this.processContext.getResponse().getMsg();
        } else {
            return RespStatusEnum.CONTEXT_IS_NULL.getMsg();
        }
    }

    public ProcessContext getProcessContext() {
        return processContext;
    }
}
