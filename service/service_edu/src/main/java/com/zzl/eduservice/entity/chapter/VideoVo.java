package com.zzl.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: 小节
 * Created by 乍暖还寒 on 2020/9/1 13:47
 * Version 1.0
 */
@Data
public class VideoVo {

    @ApiModelProperty(value = "视频ID")
    private String id;
    @ApiModelProperty(value = "节点名称")
    private String title;
    @ApiModelProperty(value = "云端视频资源id")
    private String videoSourceId;
}
