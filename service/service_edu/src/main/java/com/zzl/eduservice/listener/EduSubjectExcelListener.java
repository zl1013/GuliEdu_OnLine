package com.zzl.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.eduservice.entity.EduSubject;
import com.zzl.eduservice.entity.excel.EduSubjectData;
import com.zzl.eduservice.service.EduSubjectService;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 12:24
 * Version 1.0
 */
//需要手动new，不能通过注解交给spring管理，不能注入其他对象
//不能实现数据库操作
//可通过构造方法直接注入
public class EduSubjectExcelListener extends AnalysisEventListener<EduSubjectData> {
    //手动注入
    public EduSubjectService subjectService;

    public EduSubjectExcelListener() {
    }

    public EduSubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行的数据读取
    @Override
    public void invoke(EduSubjectData data, AnalysisContext context) {
         if (data == null){
             try {
                 throw new Exception("文件内容为空");
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }else {
            //一行一行读取，每行两个值
             //判断一级分类是否存在
             EduSubject eduSubjectOne = this.existSubjectOne(subjectService, data.getSubjectNameOne());
             if (eduSubjectOne == null){    //为空则说明数据库中不存在该记录
                 eduSubjectOne = new EduSubject();
                 eduSubjectOne.setParentId("0");
                 eduSubjectOne.setTitle(data.getSubjectNameOne());
                 subjectService.save(eduSubjectOne);
             }
             //获取parent_id
             String pid = eduSubjectOne.getId();
             //添加二级分类
             //判断二级分类是否存在
             EduSubject eduSubjectTwo = this.existSubjectTwo(subjectService, data.getSubjectNameTwo(), pid);
             if (eduSubjectTwo == null){    //为空则说明数据库中不存在该记录
                 eduSubjectTwo = new EduSubject();
                 eduSubjectTwo.setParentId(pid);
                 eduSubjectTwo.setTitle(data.getSubjectNameTwo());
                 subjectService.save(eduSubjectTwo);
             }


         }
    }
    //判断一级分类是否存在，不能重复添加

    private EduSubject existSubjectOne(EduSubjectService subjectService,String name){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",0);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }
    //判断二级分类是否存在，不能重复添加
    private EduSubject existSubjectTwo(EduSubjectService subjectService,String name,String pid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }

    //读取完成之后执行的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
