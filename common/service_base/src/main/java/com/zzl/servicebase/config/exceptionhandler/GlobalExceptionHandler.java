package com.zzl.servicebase.config.exceptionhandler;

import com.baomidou.mybatisplus.extension.api.R;
import com.zzl.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/28 18:33
 * Version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("服务器出错了");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(LangJuException.class)
    @ResponseBody //为了返回数据
    public Result error(LangJuException e) {
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
