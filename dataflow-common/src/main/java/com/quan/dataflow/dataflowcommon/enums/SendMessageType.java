package com.quan.dataflow.dataflowcommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


/**
 * @Author Hilda
 * @Description     发送信息状态枚举
 * @Date 16:41 2022/4/25
 * @Param
 * @returnValue
 **/



@Getter
@ToString
@AllArgsConstructor
public enum SendMessageType {

    TEXT(10, "文本"),
    VOICE(20, "语音"),
    VIDEO(30, "视频"),
    NEWS(40, "图文"),
    TEXT_CARD(50, "文本卡片"),
    FILE(60, "文件"),
    MINI_PROGRAM_NOTICE(70, "小程序通知"),
    MARKDOWN(80, "markdown"),
    TEMPLATE_CARD(90, "模板卡片"),
    IMAGE(100, "图片"),
    ;

    private Integer code;
    private String description;

}
