package com.zzl.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.subject.SubjectOne;
import com.zzl.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(value = "课程分类管理",tags = "课程分类管理")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传文件，读取内容
    @PostMapping("addSubject")
    @ApiOperation(value = "通过上传文件新增课程分类")
    public Result addSubject(MultipartFile file){
        //上传过来的文件
        try {
            subjectService.addSubject(file,subjectService);
            return Result.success().message("课程分类添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("导入失败");
        }
    }

    //课程分类列表（树形结构）
    @GetMapping("getAllSubject")
    @ApiOperation(value = "课程分类列表")
    public Result getAllSubject(){
        List<SubjectOne> list = subjectService.getAllSubject();

        return Result.success().data("list",list);
    }
}

