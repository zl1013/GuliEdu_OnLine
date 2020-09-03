package com.zzl.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.zzl.commonutils.Result;
import com.zzl.vod.service.VodService;
import com.zzl.vod.utils.ConstantPropertiesUtils;
import com.zzl.vod.utils.InitAcsClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/2 21:17
 * Version 1.0
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVedio(MultipartFile file) {
        try {
            //title 文件存储名称
            //fileName  文件原始名称
            //inputStream 文件上传流
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET, title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void videoList(List<String> videoList) {
        try {
            //初始化client对象
            DefaultAcsClient client = InitAcsClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            //创建操作视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //将videoList转换为(1,2,3格式)
            String videoIds = StringUtils.join(videoList.toArray(), ",");
            //设置操作的视频的id
            request.setVideoIds(videoIds);
            //发送操作
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}
