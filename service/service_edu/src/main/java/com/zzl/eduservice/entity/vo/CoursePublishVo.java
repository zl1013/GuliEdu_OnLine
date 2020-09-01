package com.zzl.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 课程发布对象
 * Created by 乍暖还寒 on 2020/9/1 23:47
 * Version 1.0
 */
@Data
public class CoursePublishVo implements Serializable {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示

}
