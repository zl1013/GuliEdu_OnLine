package com.zzl.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzl.eduservice.entity.fronfvo.CourseWebVo;
import com.zzl.eduservice.entity.fronfvo.FrontCourseInfoVo;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.entity.vo.CoursePublishVo;
import com.zzl.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息
    String addCourseInfo(CourseInfoVo courseInfoVo);
    //根据id查询课程信息
    CourseInfoVo getCourseInfo(String courseId);
    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String courseId);
    //课程发布
    void publish(String courseId);

    //根据课程id删除课程
    boolean deleteCourse(String courseId);

    //前端条件查询带分页
    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, FrontCourseInfoVo courseInfoVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
