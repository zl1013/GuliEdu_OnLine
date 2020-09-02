package com.zzl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.EduCourseDescription;
import com.zzl.eduservice.entity.chapter.ChapterVo;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.entity.vo.CoursePublishVo;
import com.zzl.eduservice.entity.vo.CourseQuery;
import com.zzl.eduservice.mapper.EduCourseMapper;
import com.zzl.eduservice.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduSubjectService subjectService;

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
        String description = null;
        try {
            description = courseDescription.getDescription();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            courseInfoVo.setDescription(description);
            return courseInfoVo;
        }


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

    @Override
    public boolean deleteCourse(String courseId) {
            //第一种方式
//        try{
//            Map<String,Object > map = new HashMap<>();
//            map.put("course_id",courseId);
//            //1.删除小节
//            videoService.removeByMap(map);
//
//            //2.删除章节
//            chapterService.removeByMap(map);
//            //3.删除描述
//            descriptionService.removeByMap(map);
//            //4.删除课程
//            baseMapper.deleteByMap(map);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false
//        }

        //第二种方式
        try{
            //1.删除小节
            videoService.removeByCourseId(courseId);
            //2.删除章节
            chapterService.removeByCourseId(courseId);
            //3.删除描述
            descriptionService.removeByCourseId(courseId);
            //4.删除课程
            baseMapper.deleteById(courseId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
