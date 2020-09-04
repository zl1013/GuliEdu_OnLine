package com.zzl.educms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.commonutils.Result;
import com.zzl.educms.entity.CrmBanner;
import com.zzl.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
@Api(value = "前端查询banner",tags = "前端查询banner")
public class FrontBannerController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("banner")
    @ApiOperation(value = "查询banner")
    public Result banner(){
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner> banners = bannerService.list(queryWrapper);
        return Result.success().data("banners",banners);
    }


}

