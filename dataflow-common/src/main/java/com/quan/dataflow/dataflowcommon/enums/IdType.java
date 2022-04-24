package com.quan.dataflow.dataflowcommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author Hilda
 * @Description     发送ID类型枚举
 * @Date 20:04 2022/4/24
 * @Param
 * @returnValue
 **/

@Getter
@ToString
@AllArgsConstructor
public enum IdType {
    USER_ID(10, "userId"),
    DID(20, "did"),
    PHONE(30, "phone"),
    OPEN_ID(40, "openId"),
    EMAIL(50, "email"),
    ENTERPRISE_USER_ID(60, "enterprise_user_id"),
    DING_DING_USER_ID(70, "ding_ding_user_id"),
    ;

    private Integer code;
    private String description;


}
