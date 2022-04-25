package com.quan.dataflow.dataflowcommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


/**
 * @Author Hilda
 * @Description     模板信息枚举
 * @Date 16:43 2022/4/25
 * @Param
 * @returnValue
 **/


@Getter
@ToString
@AllArgsConstructor
public enum TemplateType {

    CLOCKING(10, "定时类的模板(后台定时调用)"),
    REALTIME(20, "实时类的模板(接口实时调用)"),
    ;

    private Integer code;
    private String description;

}