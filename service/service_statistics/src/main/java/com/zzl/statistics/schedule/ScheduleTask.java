package com.zzl.statistics.schedule;

import cn.hutool.core.date.DateUtil;
import com.zzl.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 22:00
 * Version 1.0
 */
@Component
public class ScheduleTask {

    @Autowired
    private DailyService dailyService;

    //每天凌晨一点，把前一天的数据进行查询添加
    @Scheduled(cron = "0 0 1 * * ? ")
    public void createStatistics(){
        dailyService.registerCount(DateUtil.format(DateUtil.yesterday(), "yyyy-MM-dd"));
    }



}
