package com.zzl.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.commonutils.JwtUtils;
import com.zzl.commonutils.Result;
import com.zzl.commonutils.ordervo.CourseOrder;
import com.zzl.eduservice.client.OrderClient;
import com.zzl.eduservice.entity.EduCourse;
import com.zzl.eduservice.entity.chapter.ChapterVo;
import com.zzl.eduservice.entity.fronfvo.CourseWebVo;
import com.zzl.eduservice.entity.fronfvo.FrontCourseInfoVo;
import com.zzl.eduservice.entity.vo.CourseInfoVo;
import com.zzl.eduservice.service.EduChapterService;
import com.zzl.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/9 13:11
 * Version 1.0
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
@Api(tags = "课程查询")
public class CourseController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @PostMapping("getCourseList/{page}/{limit}")
    @ApiOperation("课程条件查询带分页")
    public Result getCourseList(
            @RequestBody(required = false) FrontCourseInfoVo courseInfoVo,
            @PathVariable long page,
            @PathVariable long limit){
        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(coursePage,courseInfoVo);

        return Result.success().data(map);
    }


    @GetMapping("frontCourseInfo/{courseId}")
    @ApiOperation(value = "课程详情")
    public Result frontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id,编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询当前课程是都已购买
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (memberId == null){
            boolean isPay = orderClient.isPay(courseId, memberId);
            return Result.success().data("chapterVideoList",chapterVideoList).data("courseWebVo",courseWebVo).data("isPay",isPay);
        }
        else {
            return Result.success().data("chapterVideoList",chapterVideoList).data("courseWebVo",courseWebVo);
        }


    }

    @GetMapping("getCourseInfoOrder/{courseId}")
    @ApiOperation(value = "根据ID查询课程信息")
    public CourseOrder getCourseInfo(@PathVariable String courseId){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        CourseOrder courseOrder = new CourseOrder();
        BeanUtils.copyProperties(courseWebVo,courseOrder);
        return courseOrder;
    }
}
