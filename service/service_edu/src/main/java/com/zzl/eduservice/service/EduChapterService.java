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

    //根据课程id查询所有章节
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
    //添加章节
    void addChapter(EduChapter chapter);
    //根据id查询章节
    EduChapter getChapterById(String id);
    //修改章节
    void updateChapter(EduChapter chapter);
    //根据id删除章节
    boolean deleteChapter(String chapterId);
    //根据courseId删除章节
    void removeByCourseId(String courseId);
}
