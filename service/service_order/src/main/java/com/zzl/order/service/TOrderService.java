package com.zzl.order.service;

import com.zzl.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberId);

}
