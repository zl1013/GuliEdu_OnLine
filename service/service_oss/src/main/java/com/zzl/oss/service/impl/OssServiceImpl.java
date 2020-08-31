package com.zzl.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zzl.oss.service.OssService;
import com.zzl.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 0:12
 * Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) throws FileNotFoundException {

        // Endpoint
        String endpoint = ConstantPropertiesUtils.END_POINT;

        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;

        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //获取文件名称后缀
        String originalFilename = file.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String fileExtension = originalFilename.substring(lastIndexOf);

        //利用UUID生成唯一的文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //重新生成文件名
        String filename = uuid + fileExtension;

        //把文件夹按日期分类
        //获取当前日期
        String datePath = new DateTime().toString("yyyy/MM/dd");

        // 上传文件流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            //调用oss方法实现上传
            //第一个参数 bucket 名称
            //第二个参数 上传到 oss 文件路径和文件名称
            //第三个参数 上传文件流
            ossClient.putObject(bucketName, "avatar/" + datePath+ "/" + filename, inputStream);

            // 关闭OSSClient
            ossClient.shutdown();

            //拼接文件路径
            String url = "https://" + bucketName + "." + endpoint + "/avatar/" + datePath + "/" + filename;
            //返回文件路径
            return url ;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
