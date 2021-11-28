package com.xuegao.xuegaocallbot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuegao.xuegaocallbot.domain.WxAccessToken;
import com.xuegao.xuegaocallbot.utils.LocalCacheUtils;
import com.xuegao.xuegaocallbot.utils.RestTemplateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/27 18:15
 */
@Service
public class WxTokenCallService {
    private static final Logger log = LoggerFactory.getLogger(WxTokenCallService.class);

    @Value("${wx.corpId}")
    private String corpId;
    @Value("${wx.access-token-url}")
    private String accessTokenUrl;

    public WxAccessToken getToken(String corpSecret) {
        WxAccessToken token = new WxAccessToken();
        Object o = LocalCacheUtils.get(corpSecret);
        if (ObjectUtils.isNotEmpty(o)) {
            log.info("[xuegao-call-bot][WxSendMessage][getToken][走缓存={}]", JSON.toJSONString(o));
            //将内容转化为JSON代码
            JSONObject json = JSON.parseObject(o.toString());
            //提取内容，放入对象
            token.setAccessToken(json.getString("access_token"));
            token.setExpiresIn(new Integer(json.getString("expires_in")));
            return token;
        }

        //访问微信服务器
        String url = accessTokenUrl + "?corpid=" + corpId + "&corpsecret=" + corpSecret;
        String result = RestTemplateUtils.sendGet(url, MediaType.APPLICATION_FORM_URLENCODED);
        log.info("[xuegao-call-bot][WxSendMessage][getToken][result={}]", JSON.toJSONString(result));
        //将内容转化为JSON代码
        JSONObject json = JSON.parseObject(result);
        //提取内容，放入对象
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresIn(new Integer(json.getString("expires_in")));
        // 重新塞入缓存
        LocalCacheUtils.put(corpSecret, result);
        return token;
    }
}