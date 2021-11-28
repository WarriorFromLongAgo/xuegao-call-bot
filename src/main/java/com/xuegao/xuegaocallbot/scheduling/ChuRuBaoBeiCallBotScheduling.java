package com.xuegao.xuegaocallbot.scheduling;

import com.xuegao.xuegaocallbot.config.MsgRangeUserIdTeShu;
import com.xuegao.xuegaocallbot.service.SendApplicationService;
import com.xuegao.xuegaocallbot.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/26 22:03
 */
@Component
public class ChuRuBaoBeiCallBotScheduling {
    private static final Logger log = LoggerFactory.getLogger(ChuRuBaoBeiCallBotScheduling.class);

    @Autowired
    private SendApplicationService sendApplicationService;

    @Autowired
    private MsgRangeUserIdTeShu msgRangeUserIdTeShu;

    @Scheduled(cron = "0 0 22 * * ? ")
    // @Scheduled(cron = "0 0/1 * * * ? ")
    public void chuRuBaoBei() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        log.info("[xuegao-call-bot][ChuRuBaoBeiCallBotScheduling][chuRuBaoBei][now={}]", now);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("提醒小助手\n");
        stringBuilder.append("这么晚了，请记得22点出入校需要报备的！！！");

        List<String> userList = msgRangeUserIdTeShu.getUserList();
        for (String userId : userList) {
            sendApplicationService.sendWeChatMessage(userId, "1", "", stringBuilder.toString(), "0",
                    SendApplicationService.SIMPLE_CALL_BOT_AGENT_ID, SendApplicationService.SIMPLE_CALL_BOT_CORP_SECRET);
            sendApplicationService.sendWeChatMessage(UserInfo.FJM, "1", "", "发送出入报备成功", "0",
                    SendApplicationService.SAVE_CALL_BOT_AGENT_ID, SendApplicationService.SAVE_CALL_BOT_CORP_SECRET);
        }
    }
}