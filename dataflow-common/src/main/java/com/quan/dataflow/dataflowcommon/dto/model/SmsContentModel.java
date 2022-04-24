package com.quan.dataflow.dataflowcommon.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SmsContentModel
 * @Description: 短信内容模型
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 19:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsContentModel extends ContentModel {

    /**
     * 短信发送内容
     */
    private String content;

    /**
     * 短信发送链接
     */
    private String url;

}
