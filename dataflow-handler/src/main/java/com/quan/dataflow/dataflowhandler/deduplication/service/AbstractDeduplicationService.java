package com.quan.dataflow.dataflowhandler.deduplication.service;

import cn.hutool.core.collection.CollUtil;
import com.quan.dataflow.dataflowcommon.constant.DataFlowConstant;
import com.quan.dataflow.dataflowcommon.domain.AnchorInfo;
import com.quan.dataflow.dataflowcommon.domain.TaskInfo;
import com.quan.dataflow.dataflowhandler.deduplication.DeduplicationHolder;
import com.quan.dataflow.dataflowhandler.domain.deduplication.DeduplicationParam;
import com.quan.dataflow.dataflowsupport.utils.LogUtils;
import com.quan.dataflow.dataflowsupport.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @ClassName: AbstractDeduplicationService
 * @Description:    去重服务抽象类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/30 9:54
 */
public abstract class AbstractDeduplicationService implements DeduplicationService {
    protected Integer deduplicationType;

    @Autowired
    private DeduplicationHolder deduplicationHolder;

    @PostConstruct
    private void init() {
        deduplicationHolder.putService(deduplicationType, this);
    }

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LogUtils logUtils;


    @Override
    public void deduplication(DeduplicationParam param) {
        TaskInfo taskInfo = param.getTaskInfo();
        Set<String> filterReceiver = new HashSet<>(taskInfo.getReceiver().size());

        // 获取redis记录
        Set<String> readyPutRedisReceiver = new HashSet<>(taskInfo.getReceiver().size());
        List<String> keys = deduplicationAllKey(taskInfo);              // 获取得到当前消息模板所有的去重Key
        Map<String, String> inRedisValue = redisUtils.mGet(keys);       // 根据所有模板的keys，获取redis已经存储的key-value，也是之前发送过的信息

        for (String receiver : taskInfo.getReceiver()) {
            String key = deduplicationSingleKey(taskInfo, receiver);
            String value = inRedisValue.get(key);

            // 符合条件的用户
            if (value != null && Integer.parseInt(value) >= param.getCountNum()) {     // getCountNum：需要达到的次数
                filterReceiver.add(receiver);           // 记录满足条件、重复的发送信息
            } else {
                readyPutRedisReceiver.add(receiver);    // 不需要去重的信息
            }
        }

        // 不符合条件的用户：需要更新Redis(无记录添加，有记录则累加次数)
        putInRedis(readyPutRedisReceiver, inRedisValue, param);     // 不需要去重的信息，更新redis的记录即可

        // 剔除符合去重条件的用户
        if (CollUtil.isNotEmpty(filterReceiver)) {
            taskInfo.getReceiver().removeAll(filterReceiver);
            logUtils.print(
                    AnchorInfo.builder()
                            .businessId(taskInfo.getBusinessId())
                            .ids(filterReceiver)
                            .state(param.getAnchorState().getCode())
                            .build()
            );
        }
    }


    /**
     * 构建去重的Key
     *      根据业务改变而改变
     *
     * @param taskInfo
     * @param receiver
     * @return
     */
    protected abstract String deduplicationSingleKey(TaskInfo taskInfo, String receiver);



    /**
     * 存入redis 实现去重
     *
     * @param readyPutRedisReceiver
     */
    private void putInRedis(Set<String> readyPutRedisReceiver,
                            Map<String, String> inRedisValue, DeduplicationParam param) {
        Map<String, String> keyValues = new HashMap<>(readyPutRedisReceiver.size());
        for (String receiver : readyPutRedisReceiver) {
            String key = deduplicationSingleKey(param.getTaskInfo(), receiver);
            if (inRedisValue.get(key) != null) {
                // 不为空，说明之前已经发送过。再次发送需要更新数据
                keyValues.put(key, String.valueOf(Integer.parseInt(inRedisValue.get(key)) + 1));
            } else {
                // 第一次发送
                keyValues.put(key, String.valueOf(DataFlowConstant.TRUE));
            }
        }
        if (CollUtil.isNotEmpty(keyValues)) {
            // pipeLine批量处理，减少与redis连接的次数
            redisUtils.pipelineSetEx(keyValues, param.getDeduplicationTime());
        }
    }


    /**
     * 获取得到当前消息模板所有的去重Key
     *
     * @param taskInfo
     * @return
     */
    private List<String> deduplicationAllKey(TaskInfo taskInfo) {
        List<String> result = new ArrayList<>(taskInfo.getReceiver().size());
        for (String receiver : taskInfo.getReceiver()) {
            String key = deduplicationSingleKey(taskInfo, receiver);
            result.add(key);
        }
        return result;
    }
}
