package com.xuegao.xuegaocallbot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuegao.xuegaocallbot.domain.WxAccessToken;
import com.xuegao.xuegaocallbot.utils.RestTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/26 0:21
 */
@Service
public class SendApplicationService {
    private static final Logger log = LoggerFactory.getLogger(SendApplicationService.class);

    /**
     * 发送应用消息的接口
     */
    private static final String MESSAGE_SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
    /**
     * family_tool
     */
    public static final String FAMILY_TOOL_AGENT_ID = "1000002";
    public static final String FAMILY_TOOL_AGENT_ID_CORP_SECRET = "DEQSFu_LbtVRueQjNvrSnjrL8tbP1rfhLhPa_7Kpong";
    /**
     * save_call_bot_agentid
     */
    public static final String SAVE_CALL_BOT_AGENT_ID = "1000004";
    public static final String SAVE_CALL_BOT_CORP_SECRET = "L2UutvJE1My066MpFUMwpl2kUJu6jbjVu59Fu1FruFU";
    /**
     * healthy_bot_agentid
     * create xi mao
     */
    public static final String HEALTHY_BOT_AGENT_ID = "1000005";
    public static final String HEALTHY_BOT_CORP_SECRET = "J2HebD86trlrx3pEB2t5lgr_2dT2_I6ScgDHTkrcElQ";
    /**
     * save_call_bot_agentid
     */
    public static final String SIMPLE_CALL_BOT_AGENT_ID = "1000007";
    public static final String SIMPLE_CALL_BOT_CORP_SECRET = "sCDrSBfZInXHTMn7ITVNb0-KAgT4O3iMk16vXgt9vRA";
    /**
     * healthy_bot_agentid
     * not create xi mao
     */
    public static final String HEALTHY_BOT_NO_CREATE_BOT_AGENT_ID = "1000008";
    public static final String HEALTHY_BOT_NO_CREATE_BOT_CORP_SECRET = "YXHh6pYcTPQ-Q_mgA7eDaWzSfwB2wpEs9FAr7LDSEoc";
    /**
     * test_call_bot_agentid
     */
    public static final String TEST_CALL_BOT_AGENT_ID = "1000009";
    public static final String TEST_CALL_BOT_CORP_SECRET = "d6lc07gQaiZ6wFn4lsD02rf8ixafK8-zVp0aV7eF1Gg";

    @Autowired
    private WxTokenCallService wxTokenCallService;

    /**
     * 发送应用消息
     *
     * @param toUser  成员ID列表
     * @param toParty 部门ID列表
     * @param toTag   标签ID列表
     * @param content 消息内容
     * @param safe    是否保密
     */
    public void sendWeChatMessage(String toUser, String toParty, String toTag, String content,
                                  String safe, String agentid, String corpSecret) {
        //从对象中提取凭证
        WxAccessToken wxAccessToken = wxTokenCallService.getToken(corpSecret);
        String accessToken = wxAccessToken.getAccessToken();
        String url = MESSAGE_SEND_URL + accessToken;

        //封装发送消息请求JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", toUser);
        jsonObject.put("toparty", toParty);
        jsonObject.put("totag", toTag);
        jsonObject.put("msgtype", "text");
        jsonObject.put("safe", safe);
        jsonObject.put("agentid", agentid);
        jsonObject.put("debug", 1);

        JSONObject text = new JSONObject();
        text.put("content", content);
        jsonObject.put("text", text);

        String result = RestTemplateUtils.sendPost(url, jsonObject.toJSONString());
        log.info("[xuegao-call-bot][SendApplicationService][sendWeChatMessage][result={}]", JSON.toJSONString(result));
        log.info("[xuegao-call-bot][SendApplicationService][sendWeChatMessage][jsonObject={}]", JSON.toJSONString(jsonObject));
        log.info("==============================================================");
    }
}