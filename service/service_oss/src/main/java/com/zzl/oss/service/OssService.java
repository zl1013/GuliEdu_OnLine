package com.zzl.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 0:12
 * Version 1.0
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file) throws FileNotFoundException;
}
