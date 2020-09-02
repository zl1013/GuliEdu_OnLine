package com.zzl.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.entity.vo.CoursePublishVo;
import com.zzl.eduservice.entity.vo.CourseQuery;
import com.zzl.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{courseId}")
    @ApiOperation(value = "根据courseId查询最终确认页数据")
    public Result getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = courseService.getPublishCourseInfo(courseId);
        return Result.success().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改数据库表中的 status 字段，由 Draft 修改为 Normal
    @GetMapping("publish/{courseId}")
    @ApiOperation(value = "课程发布")
    public Result publish(@PathVariable String courseId){
        courseService.publish(courseId);
        return Result.success().message("课程发布成功");
    }

    //课程列表、带条件查询和分页
    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping("getCourseList/{current}/{limit}")
    public Result getCourseList(@ApiParam(name = "current",value = "当前页码",required = true)
                                @PathVariable Long current,   //当前页
                                @ApiParam(name = "limit",value = "每页记录数",required = true)
                                @PathVariable Long limit,     //每页大小
                                @RequestBody CourseQuery courseQuery){
        String title = courseQuery.getTitle();
        String  status = courseQuery.getStatus();
        //查询条件
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)){
            courseQueryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)){
            if (status.equals("0")){
            courseQueryWrapper.eq("status","Draft");
            }else if (status.equals("1")){
                courseQueryWrapper.eq("status","Normal");
            }
        }
//

        //分页
        Page<EduCourse> page = new Page<EduCourse>(current,limit);

        //排序
        courseQueryWrapper.orderByDesc("gmt_create");
        //查询
        courseService.page(page, courseQueryWrapper);
        long total = page.getTotal();//总数
        List<EduCourse> records = page.getRecords();//当前页数据
        System.out.println(records);
        return Result.success().data("total",total).data("courses",records);
    }
    //根据id删除课程
    @DeleteMapping("deleteCourse/{courseId}")
    @ApiOperation("根据课程id删除课程及其相关信息")
    public Result deleteCourse(@PathVariable String courseId){
        System.out.println(courseId);
        boolean deleteCourse = courseService.deleteCourse(courseId);
        if (deleteCourse){
            return  Result.success().message("课程删除成功");
        }else{
            return Result.error().message("删除失败，请重试！");
        }
    }


}

