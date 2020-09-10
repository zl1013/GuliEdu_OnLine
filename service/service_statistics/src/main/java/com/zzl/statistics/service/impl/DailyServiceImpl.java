package com.zzl.statistics.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.statistics.client.UcenterClient;
import com.zzl.statistics.entity.Daily;
import com.zzl.statistics.mapper.DailyMapper;
import com.zzl.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {
        //添加之前先删除已经存在的
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        //获取某一天注册总数
        Integer count = ucenterClient.countRegisterDay(day);
        Daily daily = new Daily();
        daily.setRegisterNum(count);//注册人数
        daily.setDateCalculated(day);//统计日期


        daily.setVideoViewNum(RandomUtil.randomInt(100,200));
        daily.setLoginNum(RandomUtil.randomInt(100,200));
        daily.setCourseNum(RandomUtil.randomInt(100,200));
        baseMapper.insert(daily);

    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        queryWrapper.select("date_calculated",type);

        List<Daily> resultList = baseMapper.selectList(queryWrapper);

        List<String> xdata = new ArrayList<>();
        List<String> ydata = new ArrayList<>();
        //数据封装
        for (Daily daily : resultList) {
            xdata.add(daily.getDateCalculated());
            switch (type){
                case "login_num":
                    ydata.add(daily.getLoginNum().toString());
                    break;
                case "register_num":
                    ydata.add(daily.getRegisterNum().toString());
                    break;
                case "video_view_num":
                    ydata.add(daily.getVideoViewNum().toString());
                    break;
                case "course_num":
                    ydata.add(daily.getCourseNum().toString());
                    break;
                default:
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("xdata",xdata);
        map.put("ydata",ydata);
        return map;
    }
}
