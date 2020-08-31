package com.zzl.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.eduservice.entity.EduSubject;
import com.zzl.eduservice.entity.excel.EduSubjectData;
import com.zzl.eduservice.entity.subject.SubjectOne;
import com.zzl.eduservice.entity.subject.SubjectTwo;
import com.zzl.eduservice.listener.EduSubjectExcelListener;
import com.zzl.eduservice.mapper.EduSubjectMapper;
import com.zzl.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SubjectOne> getAllSubject() {

        //方式一
        //缺点：与数据库交互过多，影响性能
        //查询所有的一级分类 paren_id=0
//        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", 0);
//        List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);
//        List<SubjectOne> list = new ArrayList<>();
//        for (EduSubject eduSubject : eduSubjects) {
//            SubjectOne subjectOne = new SubjectOne();
//            QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
//            queryWrapper2.eq("parent_id", eduSubject.getId());
//            List<EduSubject> eduSubjects1 = baseMapper.selectList(queryWrapper2);
//            subjectOne.setId(eduSubject.getId());
//            subjectOne.setTitle(eduSubject.getTitle());
//            List<SubjectTwo> list2 = new ArrayList<>();
//            //查询一级分类下的所有的二级分类
//            for (EduSubject subject : eduSubjects1) {
//                SubjectTwo subjectTwo = new SubjectTwo();
//                subjectTwo.setId(subject.getId());
//                subjectTwo.setTitle(subject.getTitle());
//                list2.add(subjectTwo);
//            }
//            //数据封装
//            subjectOne.setChildren(list2);
//            list.add(subjectOne);
//        }
//        return list;

        //方式二

        //查询所有的一级分类 paren_id == 0
        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("parent_id",0);
        List<EduSubject> eduSubjects1 = baseMapper.selectList(queryWrapper1);

        //查询所有的二级分类 paren_id != 0
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id",0);
        List<EduSubject> eduSubjects2 = baseMapper.selectList(queryWrapper2);

        //数据封装
        List<SubjectOne> list1 = new ArrayList<>();
        //封装一级分类
        for (EduSubject eduSubject : eduSubjects1) {
            SubjectOne subjectOne = new SubjectOne();
//            subjectOne.setId(eduSubject.getId());
//            subjectOne.setTitle(eduSubject.getTitle());
            //可以使用spring提供的工具类
            //把 eduSubject 中的值对应的复制到 subjectOne 中
            //属性字段相同
            BeanUtils.copyProperties(eduSubject,subjectOne);

            //封装二级分类
            List<SubjectTwo> subjectTwos = new ArrayList<>();
            for (EduSubject subject : eduSubjects2) {
                if (subject.getParentId().equals(eduSubject.getId())){
                    SubjectTwo subjectTwo = new SubjectTwo();
                    BeanUtils.copyProperties(subject,subjectTwo);
                    subjectTwos.add(subjectTwo);
                }
            }
            subjectOne.setChildren(subjectTwos);
            list1.add(subjectOne);
        }

        return list1;
    }
}
