package com.zzl.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.eduservice.entity.EduChapter;
import com.zzl.eduservice.entity.EduVideo;
import com.zzl.eduservice.entity.chapter.ChapterVo;
import com.zzl.eduservice.entity.chapter.VideoVo;
import com.zzl.eduservice.mapper.EduChapterMapper;
import com.zzl.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzl.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-08-31
 */
@Service
@Transactional
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        List<ChapterVo> chapterVos = new ArrayList<>();
        //1.根据courseId查询所有的 chapter
        QueryWrapper<EduChapter> queryWrapperchapter = new QueryWrapper<>();
        queryWrapperchapter.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapperchapter);
        for (EduChapter eduChapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //2. 根据 chapter_id 查询所有的 video
            QueryWrapper<EduVideo> queryWrappervideo = new QueryWrapper<>();
            queryWrappervideo.eq("chapter_id",eduChapter.getId());
            List<VideoVo> videoVos = new ArrayList<>();
            List<EduVideo> eduVideos = videoService.list(queryWrappervideo);
            for (EduVideo eduVideo : eduVideos) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo,videoVo);
                videoVos.add(videoVo);
            }
            chapterVo.setChildren(videoVos);
            chapterVos.add(chapterVo);
        }
        return chapterVos;
    }

    @Override
    public void addChapter(EduChapter eduChapter) {
        baseMapper.insert(eduChapter);
    }

    @Override
    public EduChapter getChapterById(String chapterId) {
        return baseMapper.selectById(chapterId);
    }

    @Override
    public void updateChapter(EduChapter chapter) {
        baseMapper.updateById(chapter);
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //根据章节id查询小节表，如果存在数据，则不进行删除
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        int count = videoService.count(queryWrapper);
        if (count > 0){
            return false;
        }else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }
}
