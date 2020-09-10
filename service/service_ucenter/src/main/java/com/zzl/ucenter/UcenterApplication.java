package com.zzl.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/5 18:00
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.zzl")
@MapperScan("com.zzl.ucenter.mapper")
//nacos注册
@EnableDiscoveryClient
//服务调用
@EnableFeignClients
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
