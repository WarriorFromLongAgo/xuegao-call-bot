package com.xuegao.xuegaocallbot.domain;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/25 23:27
 */
public class WxAccessToken {

    //获取到的access_token字符串
    private String accessToken;
    //有效时间（2h，7200s）
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
// ————————————————
// 版权声明：本文为CSDN博主「Akazia」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
// 原文链接：https://blog.csdn.net/weixin_43148731/article/details/107252747