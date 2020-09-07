package com.zzl.msmservice.service;

import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/5 16:40
 * Version 1.0
 */
public interface MsmService {
    boolean sendMsm(String phone,String code);

    boolean sendMsm(Map<String,Object> phone, String code);
}
