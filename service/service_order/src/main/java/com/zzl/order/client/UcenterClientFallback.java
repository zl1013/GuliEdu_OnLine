package com.zzl.order.client;

import com.zzl.commonutils.Result;
import com.zzl.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 13:03
 * Version 1.0
 */
@Component
public class UcenterClientFallback implements UcenterClient {

    @Override
    public UcenterMemberOrder getMemberInfoByIdOrder(String id) {
        return null;
    }
}
