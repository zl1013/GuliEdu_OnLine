package com.zzl.eduservice.service.impl;

import com.zzl.eduservice.entity.EduTeacher;
import com.zzl.eduservice.mapper.EduTeacherMapper;
import com.zzl.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-28
 */
@Service
@Transactional
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

}
