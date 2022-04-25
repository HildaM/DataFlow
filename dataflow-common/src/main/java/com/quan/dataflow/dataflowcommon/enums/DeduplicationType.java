package com.quan.dataflow.dataflowcommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Hilda
 * @Description     去重信息枚举
 * @Date 16:38 2022/4/25
 * @Param
 * @returnValue
 **/


@Getter
@ToString
@AllArgsConstructor
public enum DeduplicationType {

    CONTENT(10, "N分钟相同内容去重"),
    FREQUENCY(20, "一天内N次相同渠道去重"),
    ;
    private Integer code;
    private String description;


    /**
     * 获取去重渠道的列表
     * @return
     */
    public static List<Integer> getDeduplicationList() {
        ArrayList<Integer> result = new ArrayList<>();
        for (DeduplicationType value : DeduplicationType.values()) {
            result.add(value.getCode());
        }
        return result;
    }
}
