package com.zzl.oss.controller;

import com.zzl.commonutils.Result;
import com.zzl.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 0:11
 * Version 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(value = "文件管理",tags = {"文件管理"})
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    @ApiOperation(value = "上传文件")
    public Result uploadOssFile(MultipartFile file){
        //获取上传文件 MultipartFile
        //返回文件上传到oss的路径
        String url = null;
        try {
            url = ossService.uploadFileAvatar(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Result.success().data("url",url);
    }
}
