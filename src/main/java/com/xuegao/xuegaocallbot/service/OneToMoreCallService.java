package com.xuegao.xuegaocallbot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuegao.xuegaocallbot.domain.GroupDTO;
import com.xuegao.xuegaocallbot.domain.WxAccessToken;
import com.xuegao.xuegaocallbot.utils.RestTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/26 0:05
 */
@Service
public class OneToMoreCallService {
    private static final Logger log = LoggerFactory.getLogger(OneToMoreCallService.class);

    /**
     * 创建群聊会话
     */
    private static final String APP_CHAT_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/appchat/create?access_token=";
    /**
     * 创建群聊会话
     */
    private static final String APP_CHAT_SEND = "https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token=";
    /**
     * 通讯录
     */
    public static final String GROUP_CALL_BOT_AGENT_ID = "1000005";
    /**
     * 通讯录
     */
    public static final String GROUP_CALL_BOT_CORP_SECRET = "J2HebD86trlrx3pEB2t5lgr_2dT2_I6ScgDHTkrcElQ";

    @Autowired
    private WxTokenCallService wxTokenCallService;

    public String createChat(GroupDTO groupDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chatid", groupDTO.getChatId());
        jsonObject.put("name", groupDTO.getChatName());
        jsonObject.put("owner", groupDTO.getOwner());
        String[] useridList = groupDTO.getUserList().split(",");
        jsonObject.put("userlist", useridList);

        WxAccessToken token = wxTokenCallService.getToken(GROUP_CALL_BOT_CORP_SECRET);
        String accessToken = token.getAccessToken();
        String url = APP_CHAT_CREATE + accessToken;

        String result = RestTemplateUtils.sendPost(url, jsonObject.toJSONString());
        log.info("[xuegao-call-bot][OneToMoreCallService][createChat][result={}]", JSON.toJSONString(result));
        return result;
    }

    public void sendChat(String chatId, String content) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chatid", chatId);
        jsonObject.put("msgtype", "text");
        jsonObject.put("safe", "1");

        JSONObject text = new JSONObject();
        text.put("content", content);
        jsonObject.put("text", text);

        WxAccessToken token = wxTokenCallService.getToken(GROUP_CALL_BOT_CORP_SECRET);
        String accessToken = token.getAccessToken();
        String url = APP_CHAT_SEND + accessToken;

        String result = RestTemplateUtils.sendPost(url, jsonObject.toJSONString());
        log.info("[xuegao-call-bot][OneToMoreCallService][sendChat][result={}]", JSON.toJSONString(result));
        log.info("[xuegao-call-bot][OneToMoreCallService][sendChat][result={}]", JSON.toJSONString(jsonObject));
        log.info("==============================================================");
    }
}