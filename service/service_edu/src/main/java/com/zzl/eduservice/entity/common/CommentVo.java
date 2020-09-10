package com.zzl.eduservice.entity.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/9 22:47
 * Version 1.0
 */
@Data
public class CommentVo implements Serializable {

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "评论内容")
    private String content;

}
