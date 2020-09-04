package com.zzl.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/4 0:24
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.zzl"})
@MapperScan("com.zzl.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
