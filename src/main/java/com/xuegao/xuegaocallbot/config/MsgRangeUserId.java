package com.xuegao.xuegaocallbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/27 17:43
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "msg-range-user-id.he-shu-zao-shui")
public class MsgRangeUserId {

    List<String> userList;

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }
}