package com.quan.dataflow.controller;

import com.quan.dataflow.dataflowserviceapi.domain.SendRequest;
import com.quan.dataflow.dataflowserviceapi.domain.SendResponse;
import com.quan.dataflow.dataflowserviceapi.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SendController
 * @Description: 发送信息接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/21 21:04
 */

@Api(tags={"发送消息"})
@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    /**
     * 发送消息接口
     * 入参完整示例：curl -XPOST "127.0.0.1:8080/send"  -H 'Content-Type: application/json'  -d '{"code":"send","messageParam":{"receiver":"13788888888","variables":{"title":"yyyyyy","contentValue":"6666164180"}},"messageTemplateId":1}'
     * @return
     */
    @ApiOperation(value = "下发接口",notes = "多渠道多类型下发消息，目前支持邮件和短信，类型支持：验证码、通知类、营销类。")
    @PostMapping("/send")
    public SendResponse send(@RequestBody SendRequest sendRequest) {
        return sendService.send(sendRequest);
    }

}
