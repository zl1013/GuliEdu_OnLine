package com.zzl.order.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.zzl.commonutils.Result;
import com.zzl.order.entity.TOrder;
import com.zzl.order.entity.TPayLog;
import com.zzl.order.mapper.TPayLogMapper;
import com.zzl.order.service.TOrderService;
import com.zzl.order.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzl.order.utils.HttpClient;
import com.zzl.servicebase.config.exceptionhandler.LangJuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-10
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

    @Autowired
    private TOrderService orderService;

    @Autowired
    private TPayLogService payLogService;

    //生成微信支付二维码
    @Override
    public Map createNative(String orderNo) {
        try {
            //1. 根据订单号查询订单信息
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);

            //2. 使用map设置生成二维码需要的参数
            Map map = new HashMap();
            map.put("appid", "wx74862e0dfcf69954");
            map.put("mch_id", "1558950191");
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("body", order.getCourseTitle());//课程标题
            map.put("out_trade_no", orderNo);//订单号
            map.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            map.put("spbill_create_ip", "127.0.0.1");
            map.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            map.put("trade_type", "NATIVE");

            //3. 发送httpclient请求，传递参数xml格式，微信支付提供的固定的地址
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置xml格式参数
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            httpClient.setHttps(true);
            //执行请求发送
            httpClient.post();

            //4. 得到发送请求返回的结果
            //返回的内容为xml格式
            String content = httpClient.getContent();
            //把xml格式转换为map集合
            Map<String,String> resultMap = WXPayUtil.xmlToMap(content);
            //最终返回数据的封装
            Map result = new HashMap();
            result.put("out_trade_no", orderNo);
            result.put("course_id", order.getCourseId());
            result.put("total_fee", order.getTotalFee());
            result.put("result_code", resultMap.get("result_code"));//返回二维码操作的状态码
            result.put("code_url", resultMap.get("code_url"));//二维码地址
            return result;
        }catch (Exception e){
            throw new LangJuException(20001,"生成二维码失败");
        }
    }

    //根据订单号查询订单支付状态
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //6、转成Map
            //7、返回
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //向支付表中添加数据，并修改订单表中数据状态
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //从map中获取订单号
        String orderNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(queryWrapper);

        //更新订单状态
        if (order.getStatus().intValue() == 1){
            return;
        }
        orderService.updateById(order.setStatus(1));
        //向支付表中添加记录
        TPayLog payLog = new TPayLog();
        payLog.setOrderNo(order.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());//订单完成时间
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONUtil.toJsonPrettyStr(map));

        baseMapper.insert(payLog);
    }




}
