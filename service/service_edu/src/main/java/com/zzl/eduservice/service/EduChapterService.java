package com.zzl.eduservice.service;

import com.zzl.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzl.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
