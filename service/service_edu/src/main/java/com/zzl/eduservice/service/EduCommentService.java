package com.zzl.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-09
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentList(Page<EduComment> commentPagepage, QueryWrapper<EduComment> queryWrapper);
}
