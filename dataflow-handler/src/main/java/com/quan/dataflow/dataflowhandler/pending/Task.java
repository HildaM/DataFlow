package com.quan.dataflow.dataflowhandler.pending;

import cn.hutool.core.collection.CollUtil;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowhandler.deduplication.DeduplicationRuleService;
import com.quan.dataflow.dataflowhandler.discard.DiscardMessageService;
import com.quan.dataflow.dataflowhandler.handler.HandlerHolder;
import com.quan.dataflow.dataflowhandler.shield.ShieldService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Task
 * @Description:    Task执行器
 *                  1. 丢弃信息
 *                  2. 通用去重功能
 *                  3. 发送信息
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 20:00
 */

@Data
@Accessors(chain = true)
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Task implements Runnable {

    @Autowired
    private HandlerHolder handlerHolder;

    // 消息去重服务
    @Autowired
    private DeduplicationRuleService deduplicationRuleService;

    // 丢弃信息服务
    @Autowired
    private DiscardMessageService discardMessageService;

    // 消息屏蔽服务
    @Autowired
    private ShieldService shieldService;

    // 信息载体
    private TaskInfo taskInfo;


    // 线程服务
    @Override
    public void run() {
        // 1. 丢弃信息
        if (discardMessageService.isDiscard(taskInfo)) {
            return;
        }

        // 2. 屏蔽信息
        shieldService.shield(taskInfo);

        // 3. 平台通用去重
        if (CollUtil.isNotEmpty(taskInfo.getReceiver())) {
            deduplicationRuleService.duplication(taskInfo);
        }

        // 4. 真正发送信息
        if (CollUtil.isNotEmpty(taskInfo.getReceiver())) {
            handlerHolder.route(taskInfo.getSendChannel()).doHandler(taskInfo);
        }
    }
}
