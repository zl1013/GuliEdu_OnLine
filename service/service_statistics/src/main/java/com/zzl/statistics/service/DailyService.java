package com.zzl.statistics.service;

import com.zzl.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
public interface DailyService extends IService<Daily> {

    void registerCount(String day);

    Map<String, Object> showData(String type, String begin, String end);
}
