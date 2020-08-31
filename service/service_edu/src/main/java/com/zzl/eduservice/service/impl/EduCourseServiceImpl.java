package com.zzl.eduservice.service.impl;

import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.EduCourseDescription;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.mapper.EduCourseMapper;
import com.zzl.eduservice.service.EduCourseDescriptionService;
import com.zzl.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表中添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        System.out.println(eduCourse);
        System.out.println(courseInfoVo);
        int insert = baseMapper.insert(eduCourse);

        String id = eduCourse.getId();

        //向课程简介表中添加课程简介信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        descriptionService.save(eduCourseDescription);

    }
}
