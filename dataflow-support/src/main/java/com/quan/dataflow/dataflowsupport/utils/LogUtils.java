package com.quan.dataflow.dataflowsupport.utils;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.CustomLogListener;

/**
 * @ClassName: LogUtils
 * @Description: 日志配置类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 17:06
 */
public class LogUtils extends CustomLogListener {


    public LogUtils() {
        super();
    }

    @Override
    public void createLog(LogDTO logDTO) throws Exception {

    }
}
