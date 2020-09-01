package com.zzl.eduservice.controller;


import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduCourse;
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

        String id = null;
        try {
            id = courseService.addCourseInfo(courseInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id == null){
            return Result.error().message("添加课程信息失败").data("courseId",id);
        }
        return Result.success().message("添加课程信息成功").data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation(value = "根据ID查询课程信息")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.success().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    @ApiOperation(value = "修改课程信息")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        try {
            courseService.updateCourseInfo(courseInfoVo);
            return Result.success().message("修改课程信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("修改课程信息失败");
        }

    }


}

