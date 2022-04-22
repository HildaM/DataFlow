package com.quan.dataflow.dataflowsupport.pipeline;

import java.util.List;

/**
 * @ClassName: ProcessTemplate
 * @Description: 业务执行模板（将责任链的逻辑串起来）
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 16:31
 */
public class ProcessTemplate {
    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }
    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}
