package com.zzl.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 14:46
 * Version 1.0
 */
//一级分类
@Data
public class SubjectOne {

    private String id;

    private String title;

    //一个一级分类里有多个二级分类
    private List<SubjectTwo> children = new ArrayList<>();

}
