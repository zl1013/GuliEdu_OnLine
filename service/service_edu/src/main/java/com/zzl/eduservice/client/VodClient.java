package com.zzl.eduservice.client;

import com.zzl.commonutils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/3 13:42
 * Version 1.0
 */
@Component
//需要调用的服务的名称
//fallback,调用失败执行的方法
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    //定义调用方法的路径
    @DeleteMapping("/eduvod/video/deleteVideo/{videoId}")
    //PathVariable必须加上参数名称
    Result deleteVideo(@PathVariable("videoId") String videoId);

    //批量删除视频
    //参数为多个视频id
    @DeleteMapping("/eduvod/video/deleteBatch")
    Result deleteBatch(@RequestParam("videoList") List<String> videoList);

}
