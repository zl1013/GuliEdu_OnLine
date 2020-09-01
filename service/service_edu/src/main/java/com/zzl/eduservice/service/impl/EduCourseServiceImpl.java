package com.zzl.eduservice.service.impl;

import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.EduCourseDescription;
import com.zzl.eduservice.entity.chapter.ChapterVo;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.entity.vo.CoursePublishVo;
import com.zzl.eduservice.mapper.EduCourseMapper;
import com.zzl.eduservice.service.EduChapterService;
import com.zzl.eduservice.service.EduCourseDescriptionService;
import com.zzl.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表中添加课程基本信息

        String id = null;
        try {
            EduCourse eduCourse = new EduCourse();
            BeanUtils.copyProperties(courseInfoVo,eduCourse);
            System.out.println(eduCourse);
            System.out.println(courseInfoVo);
            baseMapper.insert(eduCourse);
            id = eduCourse.getId();
            //向课程简介表中添加课程简介信息
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(id);
            eduCourseDescription.setDescription(courseInfoVo.getDescription());
            descriptionService.save(eduCourseDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return id;
        }
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);
        //查询课程描述表
        EduCourseDescription courseDescription = descriptionService.getById(courseInfoVo.getId());
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        baseMapper.updateById(eduCourse);


        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        descriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void publish(String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        baseMapper.updateById(eduCourse);
    }

}
