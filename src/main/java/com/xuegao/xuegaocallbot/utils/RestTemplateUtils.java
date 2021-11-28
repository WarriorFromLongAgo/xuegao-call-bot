package com.xuegao.xuegaocallbot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/27 18:18
 */
@Component
public class RestTemplateUtils {

    private static RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        RestTemplateUtils.restTemplate = restTemplate;
    }

    private RestTemplateUtils() {
    }

    /**
     * 发送post消息
     */
    public static String sendPost(String url, String param) {
        return sendPost(url, param, MediaType.APPLICATION_JSON);
    }

    /**
     * 发送post消息
     */
    public static String sendPost(String url, String param, MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
        return restTemplate.postForObject(url, httpEntity, String.class);
    }

    public static String sendGet(String url) {
        return sendGet(url, MediaType.APPLICATION_FORM_URLENCODED);
    }

    public static String sendGet(String url, MediaType contentType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.getForObject(url, String.class, httpEntity);
    }
}