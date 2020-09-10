package com.zzl.order.controller;


import com.zzl.commonutils.Result;
import com.zzl.order.service.TOrderService;
import com.zzl.order.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;

    @Autowired
    private TOrderService orderService;

    //生成微信支付二维码
    @GetMapping("createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo){
        //返回信息，包含二维码地址，还有其他信息
        Map map = payLogService.createNative(orderNo);
        System.out.println("+++++++++二维码集合"+map);
        return Result.success().data(map);
    }

    //根据订单号查询订单支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*********查询订单状态"+map);
        if (map == null){
            return Result.error().message("支付出错");
        }
        //如果返回map不为空,通过map获取订单状态
        if (map.get("trade_state").equals("SUCCESS")){
            //支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrderStatus(map);
            return Result.success();
        }
        return Result.success().message("支付中");

    }

}

