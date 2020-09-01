package com.zzl.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 章节
 * Created by 乍暖还寒 on 2020/9/1 13:46
 * Version 1.0
 */
@Data
public class ChapterVo {

    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    //小结
    private List<VideoVo> children = new ArrayList<>();

}
