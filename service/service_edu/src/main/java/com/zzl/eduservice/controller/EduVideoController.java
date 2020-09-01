package com.zzl.eduservice.controller;


import com.zzl.commonutils.Result;
import com.zzl.eduservice.entity.EduVideo;
import com.zzl.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
@Api(value = "小节管理",tags = "小节管理")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("/addVideo")
    @ApiOperation(value = "添加小节")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        System.out.println(eduVideo);
        videoService.addVideo(eduVideo);
        return Result.success().message("添加成功");
    }

    //修改小节
    @PutMapping("/updateVideo")
    @ApiOperation(value = "修改小节")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateVideo(eduVideo);
        return Result.success().message("修改成功");
    }
    //删除小节
    //TODO后面这个方法需要完善：删除小节的时候，同时把里面的视频删除
    @DeleteMapping("/deleteVideo/{videoId}")
    @ApiOperation(value = "删除小节")
    public Result deleteVideo(@PathVariable String videoId){
        videoService.deleteVideo(videoId);
        return Result.success().message("删除成功");
    }

    //根据video_id查询小节
    @GetMapping("/getVideo/{videoId}")
    @ApiOperation(value = "通过id查询小节")
    public Result getVideo(@PathVariable String videoId){
        EduVideo video = videoService.getVideo(videoId);
        return Result.success().data("video",video);
    }


}

