package com.zzl.ucenter.msm;

import com.zzl.commonutils.Result;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/6 23:49
 * Version 1.0
 */
@Component
public class MsmFeignClient implements MsmClient{
    @Override
    public Result sendMsm(String phone) {
        return Result.error().message("发送验证码失败");
    }
}
