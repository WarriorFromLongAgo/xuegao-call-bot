package com.xuegao.xuegaocallbot.controller;

import com.alibaba.fastjson.JSON;
import com.xuegao.xuegaocallbot.config.MsgRangeUserId;
import com.xuegao.xuegaocallbot.service.SendApplicationService;
import com.xuegao.xuegaocallbot.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/26 0:29
 */
@RestController
public class TestController {

    @Autowired
    private SendApplicationService sendApplicationService;
    @Value("${userId.fjm:fjm}")
    private String userIdFjm;

    @Autowired
    private MsgRangeUserId msgRangeUserId;

    @GetMapping("/test")
    public String test() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("提醒提肛小助手\n");
        stringBuilder.append("大家好，我是本群的“提醒提肛小助手”\n");
        stringBuilder.append("希望此刻看到消息的人可以立刻开始提肛。\n");
        stringBuilder.append("提肛运动可以帮助预防痔疮等肛周疾病。\n");
        stringBuilder.append("一小时后我会继续来群里提醒大家提肛，\n");
        stringBuilder.append("一起和我成为一天提肛50次的人吧!");
        sendApplicationService.sendWeChatMessage(UserInfo.JIU_99, "1", "", stringBuilder.toString(), "0",
                SendApplicationService.TEST_CALL_BOT_AGENT_ID, SendApplicationService.TEST_CALL_BOT_CORP_SECRET);
        sendApplicationService.sendWeChatMessage(UserInfo.FJM, "1", "", "发送提肛成功", "0",
                SendApplicationService.SAVE_CALL_BOT_AGENT_ID, SendApplicationService.SAVE_CALL_BOT_CORP_SECRET);
        return "success";
    }

    @GetMapping("/test2")
    public String test2() {

        return JSON.toJSONString(msgRangeUserId.getUserList());
    }
}