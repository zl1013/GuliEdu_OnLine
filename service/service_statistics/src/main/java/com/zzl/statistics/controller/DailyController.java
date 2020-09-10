package com.zzl.statistics.controller;


import com.zzl.commonutils.Result;
import com.zzl.statistics.client.UcenterClient;
import com.zzl.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
@Api(tags = "数据统计")
public class DailyController {



    @Autowired
    private DailyService dailyService;

    //统计某天注册人数
    @GetMapping("registerCount/{day}")
    @ApiOperation(value = "统计某天注册人数")
    public Result registerCount(@PathVariable String day){
        dailyService.registerCount(day);
        return Result.success().message("生成成功！");
    }

    //图标显示，返回两部分数据：一部分是x轴：时间，一部分是y轴：数据
    //格式为json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    @ApiOperation(value = "查询图表数据")
    public Result showData(@PathVariable String type,
                           @PathVariable String begin,
                           @PathVariable String end){
        Map<String,Object> result = dailyService.showData(type,begin,end);
        return Result.success().data(result);
    }
}

