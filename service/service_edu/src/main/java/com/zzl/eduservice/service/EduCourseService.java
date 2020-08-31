package com.zzl.eduservice.service;

import com.zzl.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzl.eduservice.entity.vo.CourseInfoVo;

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
    void addCourseInfo(CourseInfoVo courseInfoVo);
}
