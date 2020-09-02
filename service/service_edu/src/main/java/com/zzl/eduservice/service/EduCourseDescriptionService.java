package com.zzl.eduservice.service;

import com.zzl.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    //根据courseId删除课程描述
    void removeByCourseId(String courseId);
}
