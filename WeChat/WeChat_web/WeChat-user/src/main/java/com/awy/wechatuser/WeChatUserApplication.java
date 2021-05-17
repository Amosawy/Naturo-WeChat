package com.awy.wechatuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.awy")
@MapperScan(basePackages = "com.awy.mapper")
public class WeChatUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatUserApplication.class, args);
    }

}
