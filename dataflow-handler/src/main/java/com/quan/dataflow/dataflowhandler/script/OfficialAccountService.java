package com.quan.dataflow.dataflowhandler.script;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.List;

/**
 * @ClassName: OfficialAccountService
 * @Description:    账号服务接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/21 19:18
 */
public interface OfficialAccountService {
    /**
     * 发送模板消息
     *
     * @param wxMpTemplateMessages 模板消息列表
     * @return
     * @throws Exception
     */
    List<String> send(List<WxMpTemplateMessage> wxMpTemplateMessages) throws Exception;

}
