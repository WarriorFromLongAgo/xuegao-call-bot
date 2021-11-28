package com.xuegao.xuegaocallbot.scheduling;

import com.alibaba.fastjson.JSON;
import com.xuegao.xuegaocallbot.config.MsgRangeUserId;
import com.xuegao.xuegaocallbot.service.SendApplicationService;
import com.xuegao.xuegaocallbot.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/26 13:59
 */
@Component
public class HeShuiYunDongCallBotScheduling {
    private static final Logger log = LoggerFactory.getLogger(HeShuiYunDongCallBotScheduling.class);

    @Value("${im.heShuiYunDong:ceshi}")
    private String content;

    @Autowired
    private SendApplicationService sendApplicationService;

    @Autowired
    private MsgRangeUserId msgRangeUserId;

    @Scheduled(cron = "0 0 10,11,12,15,16,17,18 * * ? ")
    // @Scheduled(cron = "0 0/1 * * * ? ")
    public void heShuiYunDong() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        log.info("[xuegao-call-bot][HeShuiYunDongCallBotScheduling][heShuiYunDong][now={}]", now);

        List<String> userList = msgRangeUserId.getUserList();
        log.info("[xuegao-call-bot][HeShuiYunDongCallBotScheduling][heShuiYunDong][userList={}]", JSON.toJSONString(userList));
        for (String userId : userList) {
            sendApplicationService.sendWeChatMessage(userId, "1", "", content, "0",
                    SendApplicationService.HEALTHY_BOT_NO_CREATE_BOT_AGENT_ID,
                    SendApplicationService.HEALTHY_BOT_NO_CREATE_BOT_CORP_SECRET);
            sendApplicationService.sendWeChatMessage(UserInfo.FJM, "1", "", "发送喝水运动成功" + userId, "0",
                    SendApplicationService.SAVE_CALL_BOT_AGENT_ID, SendApplicationService.SAVE_CALL_BOT_CORP_SECRET);
        }
    }
}