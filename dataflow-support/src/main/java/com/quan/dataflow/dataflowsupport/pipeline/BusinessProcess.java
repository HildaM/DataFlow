package com.quan.dataflow.dataflowsupport.pipeline;/**
* @ClassName: BusinessProcess
* @Description: 业务执行器
* @author: Hilda   Hilda_quan@163.com
* @date: 2022/4/22 16:32
*/
public interface BusinessProcess<T extends ProcessModel> {
    /**
     * 真正处理逻辑
     * @param context
     */
    void process(ProcessContext<T> context);
}
