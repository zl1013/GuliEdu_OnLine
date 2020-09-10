package com.zzl.ucenter.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzl.commonutils.JwtUtils;
import com.zzl.ucenter.entity.UcenterMember;
import com.zzl.ucenter.service.UcenterMemberService;
import com.zzl.ucenter.utils.WXUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/7 17:02
 * Version 1.0
 */
//@RestController
@Controller//只请求地址，不返回数据
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WXApiController {

    @Autowired
    private UcenterMemberService memberService;
    //获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code){
        //1.获取code值，临时票据，类似验证码
        //2. 使用code请求 微信固定地址 ，得到access_token 和 openid
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        //拼接三个参数 ：id  秘钥 和 code值
        String accessTokenUrl = String.format(
                baseAccessTokenUrl,
                WXUtils.WX_OPEN_APP_ID,
                WXUtils.WX_OPEN_APP_SECRET,
                code
        );

        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        String body = response.body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String access_token = jsonObject.get("access_token",String.class);
        String openid = jsonObject.get("openid", String.class);



        //扫码人信息添加到数据库
        //判断数据库是否存在相同的openid
        UcenterMember ucenterMember = memberService.getMemberByOpenId(openid);
        if (ucenterMember == null){//member为空，则添加

            //3.使用access_token 和 openid 再去请求微信提供的固定地址，获取到扫描人信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(
                    baseUserInfoUrl,
                    access_token,
                    openid
            );
            HttpResponse userInfoResponse = HttpRequest.get(userInfoUrl).execute();
            String userInfoBody = userInfoResponse.body();

            JSONObject jsonUser = JSONUtil.parseObj(userInfoBody);
            String nickname = jsonUser.get("nickname", String.class);
            String headimgurl = jsonUser.get("headimgurl", String.class);
            headimgurl.replace("/","");

            ucenterMember = new UcenterMember();
            ucenterMember.setOpenid(openid);
            ucenterMember.setNickname(nickname);
            ucenterMember.setAvatar(headimgurl);
            memberService.save(ucenterMember);
        }
        //使用jwt生成token，通过路径传递
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());

        return "redirect:http://localhost:3000?token="+jwtToken;
    }

    //生成微信扫描二维码
    @GetMapping("login")
    public String getWXCode(){
//        //固定地址拼接参数
//        String url = "https://open.weixin.qq.com/" +
//                "connect/qrconnect?appid="+ WXUtils.WX_OPEN_APP_ID+"&response_type=code";;

        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = WXUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        }catch(Exception e) {
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                WXUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "langju"
        );

        //重定向到请求微信地址
        return "redirect:"+url;
    }

}
