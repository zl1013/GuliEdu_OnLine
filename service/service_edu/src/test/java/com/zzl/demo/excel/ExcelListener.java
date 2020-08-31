package com.zzl.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zzl.demo.excel.entry.ExcelDemo;

import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/31 12:03
 * Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<ExcelDemo> {
    //一行一行读取excel内容
    @Override
    public void invoke(ExcelDemo data, AnalysisContext context) {
        System.out.println(data);
    }
    //读取表头内容


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    //读取完成之后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
