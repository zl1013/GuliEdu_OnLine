package com.zzl.order.service;

import com.zzl.order.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
