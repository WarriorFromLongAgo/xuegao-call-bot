package com.xuegao.xuegaocallbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.xuegao.xuegaocallbot")
@EnableScheduling
public class XuegaoCallBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuegaoCallBotApplication.class, args);
    }

}
