package com.zzl.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.EduTeacher;
import com.zzl.eduservice.service.EduCourseService;
import com.zzl.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/9 11:03
 * Version 1.0
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
@Api(value = "前端教师查询",tags = "前端教师查询")
@CrossOrigin
public class TeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @PostMapping("getTeacherList/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师列表")
    public Result getTeacherList(@ApiParam(name = "current",value = "当前页码",required = true)
                                     @PathVariable Long current,
                                 @ApiParam(name = "limit",value = "每页记录数",required = true)
                                     @PathVariable Long limit){

        Page<EduTeacher> page = new Page<>(current,limit);

        teacherService.page(page, null);
        List<EduTeacher> records = page.getRecords();
        long pageCurrent = page.getCurrent();
        long total = page.getTotal();
        long pages = page.getPages();
        long size = page.getSize();

        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return Result.success().data(map);
    }

    //讲师详情
    @GetMapping("getTeacherInfo/{id}")
    @ApiOperation("查询讲师信息")
    public Result getTeacherInfo(@PathVariable String id){
        //讲师信息
        EduTeacher teacher = teacherService.getById(id);

        //课程信息
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        List<EduCourse> courseList = courseService.list(queryWrapper);
        return Result.success().data("teacher",teacher).data("courseList",courseList);
    }
}
