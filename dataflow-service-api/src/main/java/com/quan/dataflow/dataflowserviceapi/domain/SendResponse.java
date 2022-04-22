package com.quan.dataflow.dataflowserviceapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: SendResponse
 * @Description: 消息封装类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/22 14:32
 */

@Data
@Accessors(chain = true)  // 不写默认为false，当该值为 true 时，对应字段的 setter 方法调用后，会返回当前对象。
@AllArgsConstructor
public class SendResponse {
    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;
}
