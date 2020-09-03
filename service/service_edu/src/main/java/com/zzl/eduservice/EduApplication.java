package com.zzl.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/28 15:49
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzl"})
//nacos注册
@EnableDiscoveryClient
//服务调用
@EnableFeignClients
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
