package com.zzl.eduservice.client;

import com.zzl.commonutils.Result;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/9 23:05
 * Version 1.0
 */
@Component
public class CommentFeignClient implements CommentClient {

    @Override
    public Result getMemberInfoBy(String id) {
        return Result.error().message("获取用户信息失败");
    }
}
