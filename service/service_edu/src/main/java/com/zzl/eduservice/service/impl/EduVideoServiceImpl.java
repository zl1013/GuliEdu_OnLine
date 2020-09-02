package com.zzl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.eduservice.entity.EduVideo;
import com.zzl.eduservice.mapper.EduVideoMapper;
import com.zzl.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    //TODO删除小节同时删除视频
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }

}
