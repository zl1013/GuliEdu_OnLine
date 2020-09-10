package com.zzl.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.commonutils.JwtUtils;
import com.zzl.commonutils.Result;
import com.zzl.order.entity.TOrder;
import com.zzl.order.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.rof.CurrencyNames_rof;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
@Api(tags = "前端订单管理")
public class TOrderController {

    @Autowired
    private TOrderService orderService;
    //1. 生成订单
    @PostMapping("createOrder/{courseId}")
    @ApiOperation("生成订单")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo = orderService.createOrder(courseId,JwtUtils.getMemberIdByJwtToken(request));
        return Result.success().data("orderNo",orderNo);
    }

    //2. 根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    @ApiOperation("根据订单id查询订单信息")
    public Result getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(queryWrapper);
        return Result.success().data("order",order);
    }
    //根据用户id，课程id，查询课程是否已购买
    @GetMapping("isPay/{courseId}/{memberId}")
    @ApiOperation(value = "根据用户id，课程id，查询课程是否已购买")
    public boolean isPay(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("status",1);
        int count = orderService.count(queryWrapper);
        System.out.println(count);
        if (count == 0){
            return false;
        }else {
            return true;
        }
    }



}

