package com.zzl.eduservice.controller;


import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.chapter.ChapterVo;
import com.zzl.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
@Api(value = "章节管理",tags = "章节管理")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @GetMapping("getChapterVideo/{courseId}")
    @ApiOperation(value = "查询所有的章节以及小节")
    public Result getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> chapterVos = chapterService.getChapterVideoByCourseId(courseId);
        return Result.success().data("list",chapterVos);
    }

}

