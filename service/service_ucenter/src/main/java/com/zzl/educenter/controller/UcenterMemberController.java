package com.zzl.educenter.controller;


import com.zzl.commonutils.JwtUtils;
import com.zzl.commonutils.Result;
import com.zzl.educenter.entity.UcenterMember;
import com.zzl.educenter.entity.vo.RegisterVo;
import com.zzl.educenter.msm.MsmClient;
import com.zzl.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-05
 */
@RestController
@RequestMapping("/educenter/member")
@Api(value = "用户中心",tags = "用户中心")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private MsmClient msmClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody UcenterMember ucenterMember){
        String token = memberService.login(ucenterMember);
        return Result.success().data("token",token);
    }

//    @GetMapping("isregisted/{mobile}")
//    @ApiOperation("根据手机号判断是否已注册")
//    public Result isregisted(@PathVariable String mobile){
//        boolean registed = memberService.registed(mobile);
//        if (registed){
//            //返回false表示手机号可用
//            return Result.error().message("用户已存在");
//        }else {
//            return Result.success().message("手机号可用");
//        }
//    }

    @GetMapping("sendCode/{phone}")
    @ApiOperation("发送验证码")
    public Result sendCode(@PathVariable String phone){
        boolean registed = memberService.registed(phone);
        System.out.println(registed);
        if (registed){
            //返回false表示手机号可用
            return Result.error().message("用户已存在");
        }else {
            Result result = msmClient.sendMsm(phone);
            if (result.getCode() == 20000){
                return Result.success().message("发送验证码成功");
            }else {
                return Result.error().message(result.getMessage());
            }
        }
    }




    @PostMapping("regist")
    @ApiOperation(value = "用户注册")
    public Result regist(@RequestBody RegisterVo registerVo){

        //判断用户手机号是否存在
        //不存在发验证码
        //存在直接提示已注册
        boolean registed = memberService.registed(registerVo.getMobile());
        if (registed){
            return Result.error().message("用户已存在");
        }
        if (!registerVo.getCode().equals(redisTemplate.opsForValue().get(registerVo.getMobile()))){
            return Result.error().message("验证码无效");
        }
        boolean regist = memberService.regist(registerVo);
        if (regist){
            redisTemplate.delete(registerVo.getMobile());
            return Result.success().message("注册成功");
        }
        return Result.error().message("注册失败");
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    @ApiOperation(value = "根据token获取用户信息")
    public Result getMemberInfo(HttpServletRequest request){
        //调用jwt工具类
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库
        UcenterMember member = memberService.getById(id);
        return Result.success().data("member",member);
    }
}

