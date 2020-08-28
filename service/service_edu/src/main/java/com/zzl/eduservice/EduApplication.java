package com.zzl.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/28 15:49
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzl"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
