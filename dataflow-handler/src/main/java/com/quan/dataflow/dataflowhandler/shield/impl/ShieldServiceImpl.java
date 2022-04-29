package com.quan.dataflow.dataflowhandler.shield.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.quan.dataflow.dataflowcommon.domain.AnchorInfo;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowcommon.enums.AnchorState;
import com.quan.dataflow.dataflowcommon.enums.ShieldType;
import com.quan.dataflow.dataflowhandler.shield.ShieldService;
import com.quan.dataflow.dataflowsupport.utils.LogUtils;
import com.quan.dataflow.dataflowsupport.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

/**
 * @ClassName: ShieldServiceImpl
 * @Description: 屏蔽服务实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/26 20:08
 */

@Service
@Slf4j
public class ShieldServiceImpl implements ShieldService {

    private static final String NIGHT_SHIELD_BUT_NEXT_DAY_SEND_KEY = "night_shield_send";

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private LogUtils logUtils;

    @Override
    public void shield(TaskInfo taskInfo) {

        /**
         * example:当消息下发至austin平台时，已经是凌晨1点，业务希望此类消息在次日的早上9点推送
         * (配合 分布式任务定时任务框架搞掂)
         */
        if (isNight()) {
            if (ShieldType.NIGHT_SHIELD.getCode().equals(taskInfo.getShieldType())) {
                logUtils.print(
                        AnchorInfo.builder()
                                .state(AnchorState.NIGHT_SHIELD.getCode())
                                .businessId(taskInfo.getBusinessId())
                                .ids(taskInfo.getReceiver())
                                .build()
                );
            }

            if (ShieldType.NIGHT_SHIELD_BUT_NEXT_DAY_SEND.getCode().equals(taskInfo.getShieldType())) {
                redisUtils.lPush(
                        NIGHT_SHIELD_BUT_NEXT_DAY_SEND_KEY, JSON.toJSONString(taskInfo, SerializerFeature.WriteClassName),
                        (DateUtil.offsetDay(new Date(), 1).getTime() / 1000) - DateUtil.currentSeconds()
                );
                logUtils.print(
                        AnchorInfo.builder()
                                .state(AnchorState.NIGHT_SHIELD_NEXT_SEND.getCode())
                                .businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver())
                                .build()
                );
            }

            taskInfo.setReceiver(new HashSet<>());
        }
    }

    /**
     * 小时 < 8 默认就认为是凌晨(夜晚)
     *
     * @return
     */
    private boolean isNight() {
        return Integer.parseInt(DateFormatUtils.format(new Date(), "HH")) < 8;

    }
}
