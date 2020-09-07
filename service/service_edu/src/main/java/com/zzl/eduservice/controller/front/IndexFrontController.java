package com.zzl.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.EduTeacher;
import com.zzl.eduservice.service.EduCourseService;
import com.zzl.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/4 1:15
 * Version 1.0
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@Api(value = "前端首页数据接口",tags = "前端首页数据接口")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("index")
    @ApiOperation(value = "首页热门讲师/课程数据")
    @Cacheable(key = "'index'",value = "courseAndTeacher")
    public Result index(){
        //查询讲师
        QueryWrapper<EduTeacher> queryWrapperTeacher = new QueryWrapper<>();
        queryWrapperTeacher.orderByAsc("id");
        queryWrapperTeacher.last("limit 4");
        List<EduTeacher> teachers = teacherService.list(queryWrapperTeacher);
        //查询课程
        QueryWrapper<EduCourse> queryWrapperCourse = new QueryWrapper<>();
        queryWrapperCourse.orderByAsc("id");
        queryWrapperCourse.last("limit 8");
        List<EduCourse> courses = courseService.list(queryWrapperCourse);
        // 返回数据
        return Result.success().data("courses",courses).data("teachers",teachers);
    }




}
