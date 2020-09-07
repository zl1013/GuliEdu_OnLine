package com.zzl.servicebase.config.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/6 0:03
 * Version 1.0
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class LangJuException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
