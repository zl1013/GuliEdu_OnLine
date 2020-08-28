package com.zzl.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 返回结果类
 * Created by 乍暖还寒 on 2020/8/28 16:55
 * Version 1.0
 */
@Data
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    //构造方法私有
    private Result(){}

    //请求成功静态方法
    public static Result success(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    //请求失败静态方法
    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
