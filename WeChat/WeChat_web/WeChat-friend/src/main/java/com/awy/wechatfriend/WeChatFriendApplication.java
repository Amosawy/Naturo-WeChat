package com.awy.wechatfriend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.awy.service.api")
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.awy")
@MapperScan(basePackages = "com.awy.mapper")
public class WeChatFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatFriendApplication.class, args);
    }

}
