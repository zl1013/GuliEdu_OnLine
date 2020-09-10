package com.zzl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.eduservice.entity.EduComment;
import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.mapper.EduCommentMapper;
import com.zzl.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-09
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {



    @Override
    public Map<String, Object> getCommentList(Page<EduComment> commentPagepage, QueryWrapper<EduComment> queryWrapper) {
        baseMapper.selectPage(commentPagepage,queryWrapper);

        List<EduComment> courseList = commentPagepage.getRecords();
        long current = commentPagepage.getCurrent();
        long pages = commentPagepage.getPages();
        long size = commentPagepage.getSize();
        long total = commentPagepage.getTotal();
        boolean hasNext = commentPagepage.hasNext();
        boolean hasPrevious = commentPagepage.hasPrevious();

        Map<String,Object> map = new HashMap<>();
        map.put("items",courseList);
        map.put("current",current);
        map.put("pages",pages);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);

        return map;
    }

}
