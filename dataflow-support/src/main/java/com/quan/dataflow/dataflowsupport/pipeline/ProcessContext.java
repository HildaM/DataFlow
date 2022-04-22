package com.quan.dataflow.dataflowsupport.pipeline;

import com.quan.dataflow.dataflowcommon.vo.BasicResultVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: ProcessContext
 * @Description: 责任链上下文
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 16:01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProcessContext<T extends ProcessModel> {

    /**
     * 标识责任链的code
     */
    private String code;

    /**
     * 存储责任链上下文数据的模型
     */
    private T processModel;

    /**
     * 责任链中断的标识
     */
    private Boolean needBreak;

    /**
     * 流程处理的结果
     */
    BasicResultVO response;
}
