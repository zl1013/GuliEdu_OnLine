package com.zzl.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.commonutils.JwtUtils;
import com.zzl.commonutils.Result;
import com.zzl.eduservice.client.CommentClient;
import com.zzl.eduservice.entity.EduComment;
import com.zzl.eduservice.entity.common.CommentVo;
import com.zzl.eduservice.service.EduCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-09
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
@Api(tags = "评论管理")
public class EduCommentController {

    @Autowired
    private CommentClient commentClient;

    @Autowired
    private EduCommentService commentService;



    @PostMapping("addComment")
    @ApiOperation(value = "添加评论")
    public Result addComment(@RequestBody CommentVo commentVo, HttpServletRequest request){
        //调用jwt工具类
        String uid = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(uid);
        Result result = commentClient.getMemberInfoBy(uid);
        System.out.println(result);
        System.out.println(result.getData());
        if (result.getCode() == 20001){
            return Result.error().message(result.getMessage());
        }
        Map<String, Object> resultData = result.getData();
        String id = (String)resultData.get("id");
        String nickname = (String)resultData.get("nickname");
        String avatar = (String) resultData.get("avatar");
        EduComment comment = new EduComment();
        BeanUtils.copyProperties(commentVo,comment);
        comment.setMemberId(id);
        comment.setAvatar(avatar);
        comment.setNickname(nickname);
        commentService.save(comment);
        return Result.success().message("添加评论成功");
    }

    @GetMapping("getCommentList/{current}/{limit}/{courseId}")
    @ApiOperation(value = "获取评论列表")
    public Result getCommentList(@PathVariable long current,@PathVariable long limit,@PathVariable String courseId){
        Page<EduComment> commentPagepage = new Page<>(current,limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.eq("course_id",courseId);
//        commentService.page(commentPagepage,queryWrapper);
        Map<String,Object> map = commentService.getCommentList(commentPagepage,queryWrapper);
        return Result.success().data(map);
    }
}

