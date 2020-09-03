package com.zzl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.eduservice.client.VodClient;
import com.zzl.eduservice.entity.EduVideo;
import com.zzl.eduservice.mapper.EduVideoMapper;
import com.zzl.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@Service
@Transactional
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void addVideo(EduVideo eduVideo) {
        baseMapper.insert(eduVideo);
    }

    @Override
    public void updateVideo(EduVideo eduVideo) {
        baseMapper.updateById(eduVideo);
    }

    @Override
    public void deleteVideo(String videoId) {
        baseMapper.deleteById(videoId);
    }

    @Override
    public EduVideo getVideo(String videoId) {
        return baseMapper.selectById(videoId);
    }

    @Override
    //删除小节同时删除视频
    public void removeByCourseId(String courseId) {
        //根据课程id查询所有视频id
        QueryWrapper<EduVideo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        queryWrapper1.select("video_source_id");
        List<EduVideo> videos = baseMapper.selectList(queryWrapper1);

        //把List<EduVideo>转化为List<String>
        List<String> list = new ArrayList<>();
        for (EduVideo video : videos) {
            if (!StringUtils.isEmpty(video.getVideoSourceId())){
                list.add(video.getVideoSourceId());
            }
        }
        if (list.size() > 0){
            vodClient.deleteBatch(list);
        }


        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id",courseId);
        baseMapper.delete(queryWrapper2);
    }

}
