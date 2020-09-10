package com.zzl.eduservice.client;

import com.zzl.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/9 23:02
 * Version 1.0
 */
@Component
@FeignClient(value = "service-ucenter",fallback = CommentFeignClient.class)
public interface CommentClient {
    //根据id获取用户信息
    @GetMapping("/educenter/member/getMemberInfoById/{id}")
    public Result getMemberInfoBy(@PathVariable String id);
}
