package com.zzl.msmservice.controller;

import com.zzl.commonutils.Result;
import com.zzl.msmservice.service.MsmService;
import com.zzl.msmservice.utils.MsmUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/5 16:40
 * Version 1.0
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
@Api(value = "短信操作",tags = "短信操作")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("send/{phone}")
    @ApiOperation(value = "发送短信验证码")
    public Result sendMsm(@PathVariable String phone){
        System.out.println(phone);
        String code = MsmUtils.getFourBitRandom();
        Map<String,Object> msm = new HashMap<>();
        msm.put("code",code);
        boolean sendMsm = msmService.sendMsm(msm, phone);
        if (sendMsm){
            //验证码放入redis
            //设置有效期
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.success();
        }else {
            return Result.error();
        }
    }
}
