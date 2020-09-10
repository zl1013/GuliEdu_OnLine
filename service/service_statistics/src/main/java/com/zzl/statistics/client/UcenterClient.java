package com.zzl.statistics.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 20:11
 * Version 1.0
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientFallback.class)
public interface UcenterClient {
    @GetMapping("/educenter/member/countRegisterDay/{day}")
    public Integer countRegisterDay(@PathVariable("day") String day);
}
