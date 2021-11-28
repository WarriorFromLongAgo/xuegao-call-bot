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
 * @date 2021/11/26 13:22
 */
@Component
public class TiGangCallBotScheduling {
    private static final Logger log = LoggerFactory.getLogger(TiGangCallBotScheduling.class);
    /**
     * ti_gang_call_bot
     */
    public static final String TI_GANG_CALL_BOT_AGENT_ID = "1000006";
    public static final String TI_GANG_CALL_BOT_CORP_SECRET = "vdsvkwkFMotiokfHT8DvL-Nk_a6u5izevhA5PWrGSKA";

    @Autowired
    private SendApplicationService sendApplicationService;
    @Autowired
    private MsgRangeUserIdTeShu msgRangeUserIdTeShu;
    // 提醒提肛小助手
    // 大家好，我是本群的“提醒提肛小助手”
    // 希望此刻看到消息的人可以立刻开始提肛。
    // 提肛运动可以帮助预防痔疮等肛周疾病。
    // 一小时后我会继续来群里提醒大家提肛，
    // -起和我成为一天提肛50次的人吧!

    @Scheduled(cron = "0 0 0,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ? ")
    // @Scheduled(cron = "0 0/1 * * * ? ")
    public void tiGangCallBot() {
        log.info("[xuegao-call-bot][TiGangCallBotScheduling][tiGangCallBot][now={}]",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("提醒提肛小助手\n");
        stringBuilder.append("大家好，我是本群的“提醒提肛小助手”\n");
        stringBuilder.append("希望此刻看到消息的人可以立刻开始提肛。\n");
        stringBuilder.append("提肛运动可以帮助预防痔疮等肛周疾病。\n");
        stringBuilder.append("一小时后我会继续来群里提醒大家提肛，\n");
        stringBuilder.append("一起和我成为一天提肛50次的人吧!");
        List<String> userList = msgRangeUserIdTeShu.getUserList();
        for (String userId : userList) {
            sendApplicationService.sendWeChatMessage(userId, "1", "", stringBuilder.toString(), "0",
                    TI_GANG_CALL_BOT_AGENT_ID, TI_GANG_CALL_BOT_CORP_SECRET);
            sendApplicationService.sendWeChatMessage(UserInfo.FJM, "1", "", "发送提肛成功", "0",
                    SendApplicationService.SAVE_CALL_BOT_AGENT_ID, SendApplicationService.SAVE_CALL_BOT_CORP_SECRET);
        }
    }
}