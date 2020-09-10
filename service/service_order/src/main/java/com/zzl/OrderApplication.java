package com.zzl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 11:42
 * Version 1.0
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.zzl"})
@MapperScan("com.zzl.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
