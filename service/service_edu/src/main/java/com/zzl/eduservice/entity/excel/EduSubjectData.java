package com.zzl.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 12:17
 * Version 1.0
 */
@Data
public class EduSubjectData {
    //一级分类
    @ExcelProperty(index = 0)
    private String subjectNameOne;

    //二级分类
    @ExcelProperty(index = 1)
    private String subjectNameTwo;
}
