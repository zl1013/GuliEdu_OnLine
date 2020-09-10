package com.zzl.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 18:47
 * Version 1.0
 */
@Component
public class OrderClientFallback implements OrderClient{
    @Override
    public boolean isPay(String courseId, String memberId) {
        return false;
    }
}
