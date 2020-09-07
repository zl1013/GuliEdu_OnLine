package com.zzl.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zzl.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/5 16:41
 * Version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {
    //发送短信
    @Override
    public boolean sendMsm(String phone, String code) {

        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        //创建发送短信的对象
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4GHF8oQB5NVXjcQuVWvn", "gaVOfhtbqo3KgC4HRD3SgLRZaVtZWi");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","浪聚在线"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_201651074"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code)); //验证码数据，转换json数据传递

        //发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendMsm(Map<String, Object> code, String phone) {
        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        //创建发送短信的对象
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4GHF8oQB5NVXjcQuVWvn", "gaVOfhtbqo3KgC4HRD3SgLRZaVtZWi");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","浪聚在线"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_201651074"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code)); //验证码数据，转换json数据传递

        //发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
