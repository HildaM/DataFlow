package com.quan.dataflow.dataflowcommon.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: EmailContentModel
 * @Description: 邮箱消息模型
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 19:29
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailContentModel extends ContentModel{

    /**
     * 标题
     */
    private String title;

    /**
     * 内容(可写入HTML)
     */
    private String content;
}
