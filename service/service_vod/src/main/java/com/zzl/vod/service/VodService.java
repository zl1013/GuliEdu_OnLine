package com.zzl.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/2 21:17
 * Version 1.0
 */
public interface VodService {
    //上传视频，返回视频id
    String uploadVedio(MultipartFile file);

    //批量删除视频
    void videoList(List<String> videoList);
}
