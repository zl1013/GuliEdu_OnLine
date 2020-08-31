package com.zzl.eduservice.controller;


import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api(value = "课程信息管理",tags = "课程信息管理")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    @ApiOperation(value = "添加课程信息")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.addCourseInfo(courseInfoVo);

        return Result.success();
    }

}

