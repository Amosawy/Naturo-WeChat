package com.awy.wechatmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.awy",exclude = DataSourceAutoConfiguration.class)
public class WeChatMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatMessageApplication.class, args);
    }

}
