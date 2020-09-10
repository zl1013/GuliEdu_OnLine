package com.zzl.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/6 0:12
 * Version 1.0
 */
@Data
@ApiModel(value = "注册对象",description = "用户注册信息")
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
