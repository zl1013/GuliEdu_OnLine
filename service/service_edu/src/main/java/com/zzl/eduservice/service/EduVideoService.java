package com.zzl.eduservice.service;

import com.zzl.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
public interface EduVideoService extends IService<EduVideo> {

    //添加小节
    void addVideo(EduVideo eduVideo);

    //修改小节
    void updateVideo(EduVideo eduVideo);

    //删除小节
    void deleteVideo(String videoId);

    //查询小节
    EduVideo getVideo(String videoId);


}
