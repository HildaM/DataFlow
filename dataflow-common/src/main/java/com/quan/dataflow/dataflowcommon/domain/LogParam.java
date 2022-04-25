package com.quan.dataflow.dataflowcommon.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LogParam
 * @Description: 日志参数
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 19:44
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogParam {

    /**
     * 需要记录的日志
     */
    private Object object;

    /**
     * 标识日志的业务
     */
    private String bizType;

    /**
     * 生成时间
     */
    private long timestamp;

}
