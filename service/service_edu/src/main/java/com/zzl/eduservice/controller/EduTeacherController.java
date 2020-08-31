package com.zzl.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduTeacher;
import com.zzl.eduservice.entity.vo.TeacherQuery;
import com.zzl.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SoundbankResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 讲师 前端控制器
 * @author 乍暖还寒
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(value = "讲师管理",tags = {"讲师管理"})
@CrossOrigin//解决跨域
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //1.查询讲师表所有数据
    //RestFul风格
    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public Result findAll(){
        try {
            List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
            return Result.success().data("list",eduTeacherList).message("查询讲师列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    //2.逻辑删除讲师
    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public Result removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return Result.success().message("删除成功");
        }else {
            return Result.error().message("删除失败");
        }
    }

    //3.分页查询方法
    // current 当前页
    // limit 每页记录数
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@ApiParam(name = "current",value = "当前页码",required = true)
                                    @PathVariable Long current,//当前页
                                  @ApiParam(name = "limit",value = "每页记录数",required = true)
                                    @PathVariable Long limit){//每页记录数
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //调用分页方法实现分页
        //调用方法的时候，底层把所有分页数据封装到page对象里面
        eduTeacherService.page(page,null);
        long total = page.getTotal();//总记录数
        List<EduTeacher> records = page.getRecords();//当前页数据

//        Map<String,Object> map = new HashMap<>();
//        map.put("total",total);
//        map.put("records",records);
//        return Result.success().message("查询成功").data(map);

        return Result.success().message("查询成功").data("total",total).data("records",records);
    }

    //4.条件查询带分页的方法
    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@ApiParam(name = "current",value = "当前页码",required = true)
                                           @PathVariable Long current,//当前页
                                       @ApiParam(name = "limit",value = "每页记录数",required = true)
                                           @PathVariable Long limit
                                        ,@RequestBody(required = false) TeacherQuery teacherQuery  ){//每页记录数
        System.out.println("分页查询");
        System.out.println(current);
        System.out.println(limit);
        System.out.println(teacherQuery);
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);

        //QueryWrapper
        QueryWrapper queryWrapper = new QueryWrapper();
        //多条件组合查询，类似mybatis动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        //排序
        queryWrapper.orderByDesc("gmt_create");

        //调用方法的时候，底层把所有分页数据封装到page对象里面
        eduTeacherService.page(page,queryWrapper);


        long total = page.getTotal();//总记录数
        List<EduTeacher> records = page.getRecords();//当前页数据
        return Result.success().message("查询成功").data("total",total).data("records",records);
    }

    //添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    //根据ID查询讲师
    @ApiOperation(value = "据ID查询讲师")
    @GetMapping("findTeacherByID/{id}")
    public Result findTeacherByID(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.success().data("teacher",teacher).message("查询成功");
    }
    //根据ID修改讲师信息
    @ApiOperation(value = "根据ID修改讲师信息")
    @PostMapping("updateTeacherByID")
    public Result updateTeacherByID(@RequestBody EduTeacher teacher){
        System.out.println("update");
        boolean update = eduTeacherService.updateById(teacher);
        if (update){
            return Result.success().message("修改成功");
        }else {
            return Result.error().message("修改失败");
        }
    }

}
