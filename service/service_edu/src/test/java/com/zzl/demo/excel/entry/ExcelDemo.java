package com.zzl.demo.excel.entry;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 11:45
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDemo {

    //设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
