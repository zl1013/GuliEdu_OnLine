package com.zzl.eduservice.service;

import com.zzl.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzl.eduservice.entity.subject.SubjectOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void addSubject(MultipartFile file, EduSubjectService subjectService);

    //查询所有的课程分类
    List<SubjectOne> getAllSubject();
}
