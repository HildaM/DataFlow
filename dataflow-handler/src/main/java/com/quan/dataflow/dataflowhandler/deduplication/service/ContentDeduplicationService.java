package com.quan.dataflow.dataflowhandler.deduplication.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.DeduplicationType;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ContentDeduplicationService
 * @Description:    内容去重服务（默认5分钟相同的文案发给相同的用户去重）
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/30 11:00
 */

@Service
public class ContentDeduplicationService extends AbstractDeduplicationService {

    public ContentDeduplicationService() {
        deduplicationType = DeduplicationType.CONTENT.getCode();
    }

    /**
     * 内容去重 构建key
     * <p>
     * key: md5(templateId + receiver + content)
     * <p>
     * 相同的内容相同的模板短时间内发给同一个人
     *
     * @param taskInfo
     * @return
     */
    @Override
    public String deduplicationSingleKey(TaskInfo taskInfo, String receiver) {
        return DigestUtil.md5Hex(taskInfo.getMessageTemplateId() + receiver
                + JSON.toJSONString(taskInfo.getContentModel()));
    }
}
