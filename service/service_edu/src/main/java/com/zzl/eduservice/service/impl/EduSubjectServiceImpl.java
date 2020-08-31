package com.zzl.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zzl.eduservice.entity.EduSubject;
import com.zzl.eduservice.entity.excel.EduSubjectData;
import com.zzl.eduservice.listener.EduSubjectExcelListener;
import com.zzl.eduservice.mapper.EduSubjectMapper;
import com.zzl.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void addSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //获取文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, EduSubjectData.class,new EduSubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
