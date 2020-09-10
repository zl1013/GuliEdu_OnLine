package com.zzl.commonutils.ordervo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 13:34
 * Version 1.0
 */
@Data
public class UcenterMemberOrder implements Serializable {

    @ApiModelProperty(value = "会员id")
    private String memberId;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员手机")
    private String mobile;
}
