package com.quan.dataflow.dataflowcommon.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ClassName: OfficialAccountsContentModel
 * @Description:    服务号消息模型
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 19:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficialAccountsContentModel extends ContentModel {

    /**
     * 模板消息发送的数据
     */
    Map<String, String> map;

    /**
     * 模板消息跳转的url
     */
    String url;

}
