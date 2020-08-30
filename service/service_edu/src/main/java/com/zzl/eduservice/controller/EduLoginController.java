package com.zzl.eduservice.controller;

import com.zzl.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/30 15:08
 * Version 1.0
 */
@Api(value = "后台登录",tags = {"后台登录"})
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域问题
public class EduLoginController {
    //login
    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Result login(){

        return Result.success().data("token","admin");
    }

    //getinfo
    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public Result getInfo(){
        return Result.success().data("roles","[admin]").data("name","admin").data("avatar","https://gitee.com/zzl_java/PicGo/raw/master/img/favicon.png").data("introduction","admin");
    }

}
