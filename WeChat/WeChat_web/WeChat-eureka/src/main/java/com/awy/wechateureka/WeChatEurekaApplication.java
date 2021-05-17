package com.awy.wechateureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WeChatEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatEurekaApplication.class, args);
    }

}
