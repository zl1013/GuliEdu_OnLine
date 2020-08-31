package com.zzl.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.zzl.demo.excel.entry.ExcelDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 11:47
 * Version 1.0
 */
public class TestEasyExcel {
//    public static void main(String[] args) {
//        //实现excel写操作
//        //1. 设置文件写出路径和excel文件名称
//        String fileName = "e:\\write.xlsx";
//
//        //2. 调用easyExcel里面的方法实现写操作
//        //write方法两个参数：第一个参数：文件路径名称、第二个参数：实体类class
//        //.sheet    设置sheet名称
//        //.doWrite  要写出的list集合
//        EasyExcel.write(fileName, ExcelDemo.class).sheet("学生列表").doWrite(getData());
//
//    }
//    //创建方法，返回list集合
//    private static List<ExcelDemo> getData(){
//        List<ExcelDemo> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ExcelDemo excelDemo = new ExcelDemo();
//            excelDemo.setSno(i);
//            excelDemo.setSname("easydemo" + i);
//            list.add(excelDemo);
//        }
//        return list;
//    }
    public static void main(String[] args) {
        //实现excel读操作
        //1. 设置读取文件写路径和excel文件名称
        String fileName = "e:\\write.xlsx";
        //2. 调用easyExcel里面的方法实现写操作
        //read方法三个参数：第一个参数：文件路径名称、第二个参数：实体类class、第三个参数：监听器
        EasyExcel.read(fileName,ExcelDemo.class,new ExcelListener()).sheet().doRead();
    }
}
