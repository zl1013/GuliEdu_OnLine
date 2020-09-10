package com.zzl.statistics.client;

import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 20:12
 * Version 1.0
 */
@Component
public class UcenterClientFallback implements UcenterClient {
    @Override
    public Integer countRegisterDay(String day) {
        return 0;
    }
}
