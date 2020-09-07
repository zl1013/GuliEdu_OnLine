package com.zzl.educenter.msm;

import com.zzl.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/6 23:48
 * Version 1.0
 */
@Component
//需要调用的服务的名称
//fallback,调用失败执行的方法
@FeignClient(value = "service-msm",fallback = MsmFeignClient.class)
public interface MsmClient {
    //定义调用方法的路径
    @GetMapping("/edumsm/msm/send/{phone}")
    //PathVariable必须加上参数名称
    Result sendMsm(@PathVariable String phone);
}