package com.zzl.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/28 15:50
 * Version 1.0
 */
@Configuration
//配置Mapper扫描
@MapperScan("com.zzl.eduservice.mapper")
public class EduServiceConfig {

    //性能测试插件
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    //配置逻辑删除
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    //配置分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
