package com.zzl.order.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zzl.commonutils.ordervo.CourseOrder;
import com.zzl.commonutils.ordervo.UcenterMemberOrder;
import com.zzl.order.client.CourseClient;
import com.zzl.order.client.UcenterClient;
import com.zzl.order.entity.TOrder;
import com.zzl.order.mapper.TOrderMapper;
import com.zzl.order.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzl.order.utils.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UcenterClient ucenterClient;

    //生成订单
    @Override
    public String createOrder(String courseId, String memberId) {
        //远程调用获取课程信息
        CourseOrder courseOrder = courseClient.getCourseInfo(courseId);
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseOrder.getId());
        order.setCourseTitle(courseOrder.getTitle());
        order.setCourseCover(courseOrder.getCover());
        order.setTeacherName(courseOrder.getTeacherName());
        order.setTotalFee(courseOrder.getPrice());

        //远程调用获取用户信息
        UcenterMemberOrder memberOrder = ucenterClient.getMemberInfoByIdOrder(memberId);
        BeanUtils.copyProperties(memberOrder,order);

        order.setStatus(0);
        order.setPayType(1);

        baseMapper.insert(order);
        System.out.println(order);
        return order.getOrderNo();
    }
}
