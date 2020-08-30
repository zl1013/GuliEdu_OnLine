package com.zzl.servicebase.config.exceptionhandler;

import com.baomidou.mybatisplus.extension.api.R;
import com.zzl.commonutils.Result;
<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> dev
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/28 18:33
 * Version 1.0
 */
@ControllerAdvice
<<<<<<< HEAD
=======
@Slf4j
>>>>>>> dev
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回数据
    public Result error(Exception e){
        e.printStackTrace();
<<<<<<< HEAD
=======
        log.error(e.getMessage());
>>>>>>> dev
        return Result.error().message("服务器出错了");
    }
}
