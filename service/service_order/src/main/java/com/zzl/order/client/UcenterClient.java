package com.zzl.order.client;

import com.zzl.commonutils.Result;
import com.zzl.commonutils.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 13:00
 * Version 1.0
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientFallback.class)
public interface UcenterClient {
    //根据id获取用户信息
    @GetMapping("/educenter/member/getMemberInfoByIdOrder/{id}")
    public UcenterMemberOrder getMemberInfoByIdOrder(@PathVariable String id);
}
