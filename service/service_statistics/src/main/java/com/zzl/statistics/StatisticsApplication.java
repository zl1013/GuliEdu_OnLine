package com.zzl.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 19:58
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzl"})
@MapperScan("com.zzl.statistics.mapper")
@EnableDiscoveryClient
@EnableFeignClients
//开启定时任务
@EnableScheduling
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
