package com.zzl.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zzl.commonutils.Result;
import com.zzl.servicebase.config.exceptionhandler.LangJuException;
import com.zzl.vod.service.VodService;
import com.zzl.vod.utils.ConstantPropertiesUtils;
import com.zzl.vod.utils.InitAcsClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/2 21:16
 * Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
@Api(value = "视频管理",tags = "视频管理")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    @ApiOperation("视频上传")
    public Result uploadVideo(MultipartFile file){
        String videoId = null;
        try {
            videoId = vodService.uploadVedio(file);
            return Result.success().data("videoId",videoId).message("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("上传失败，请重试！");
        }
    }
    @DeleteMapping("deleteVideo/{videoId}")
    @ApiOperation("根据id删除视频")
    public Result deleteVideo(@PathVariable String videoId){
        try {
            DefaultAcsClient client = InitAcsClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
            return Result.success();
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    //批量删除视频
    //参数为多个视频id
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("videoList") List videoList){
        vodService.videoList(videoList);
        return Result.success();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id){

        try {
            //创建初始化对象
            DefaultAcsClient client = InitAcsClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //向request对象里面设置视频id
            request.setVideoId(id);
            //调用初始化对象里面的方法传递request，获取数据
            response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return Result.success().data("playAuth",playAuth);
        } catch (ClientException e) {
            throw new LangJuException(20001,"获取凭证失败");
        }
    }
}
