package com.zzl.eduservice.client;

import com.zzl.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/3 16:56
 * Version 1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    //方法执行成功执行VodClient接口中的方法
    //方法执行失败执行VodClient接口实现类中同名的方法
    @Override
    public Result deleteVideo(String videoId) {
        return Result.error().message("删除出错");
    }

    @Override
    public Result deleteBatch(List<String> videoList) {
        return Result.error().message("删除多个视频出错");
    }
}
