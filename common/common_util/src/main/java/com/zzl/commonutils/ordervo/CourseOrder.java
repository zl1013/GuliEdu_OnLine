package com.zzl.commonutils.ordervo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/9 17:51
 * Version 1.0
 */

@ApiModel(value="课程信息", description="网站课程详情页需要的相关字段")
@Data
public class CourseOrder implements Serializable {

    private String id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
}
