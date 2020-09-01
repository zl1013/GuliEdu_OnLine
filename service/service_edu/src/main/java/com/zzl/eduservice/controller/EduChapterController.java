package com.zzl.eduservice.controller;


import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduChapter;
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

    @PostMapping("addChapter")
    @ApiOperation(value = "添加章节")
    public Result addChapter(@RequestBody EduChapter chapter){
        chapterService.addChapter(chapter);
        return Result.success().message("添加成功");
    }

    @GetMapping("getChapterById/{chapterId}")
    @ApiOperation(value = "根据id查询章节")
    public Result getChapterById(@PathVariable String chapterId){
        EduChapter chapterById = chapterService.getChapterById(chapterId);
        return Result.success().message("查询成功").data("chapter",chapterById);
    }

    @PutMapping("updateChapter")
    @ApiOperation(value = "修改章节")
    public Result updateChapter(@RequestBody EduChapter chapter){
        chapterService.updateChapter(chapter);
        return Result.success().message("修改成功");
    }

    @DeleteMapping("deleteChapter/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        boolean deleteChapter = chapterService.deleteChapter(chapterId);
        if (deleteChapter){
            return Result.success().message("删除成功");
        }else {
            return Result.error().message("删除失败，请先删除该章节下面的所有小节");
        }
    }

}

