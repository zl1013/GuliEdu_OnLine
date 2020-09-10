package com.zzl.eduservice.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 18:46
 * Version 1.0
 */
@Component
@FeignClient(value = "service-order",fallback = OrderClientFallback.class)
public interface OrderClient {
    //根据用户id，课程id，查询课程是否已购买
    @GetMapping("/eduorder/order/isPay/{courseId}/{memberId}")
    public boolean isPay(@PathVariable String courseId,@PathVariable String memberId);
}
