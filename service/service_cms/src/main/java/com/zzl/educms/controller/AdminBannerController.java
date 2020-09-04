package com.zzl.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzl.commonutils.Result;
import com.zzl.educms.entity.CrmBanner;
import com.zzl.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  管理员banner管理接口
 *
 * @author 乍暖还寒
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
@Api(value = "管理员banner管理接口",tags = "管理员banner管理接口")
public class AdminBannerController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("getPage/{page}/{limit}")
    @ApiOperation("管理员端banner分页查询")
    public Result getPage(@PathVariable long page,@PathVariable long limit){
        //创建分页对象
        Page<CrmBanner> crmBannerPage = new Page<>(page,limit);
        //查询分页
        bannerService.page(crmBannerPage,null);
        return Result.success().data("page",crmBannerPage.getRecords()).data("total",crmBannerPage.getTotal());
    }

    @GetMapping("getbanner/{bannerId}")
    @ApiOperation("管理员端根据bannerId查询banner详情")
    public Result getbanner(@PathVariable String bannerId){
        CrmBanner banner = bannerService.getById(bannerId);
        return Result.success().data("banner",banner);
    }

    @PostMapping("addbanner")
    @ApiOperation("管理员端新增banner")
    public Result addbanner(@RequestBody CrmBanner banner){
        bannerService.save(banner);
        return Result.success().message("添加成功！");
    }

    @PostMapping("updatebanner")
    @ApiOperation("管理员端更新banner")
    public Result updatebanner(@RequestBody CrmBanner banner){
        bannerService.updateById(banner);
        return Result.success().message("修改成功！");
    }

    @DeleteMapping("deletebanner/{bannerId}")
    @ApiOperation("管理员端更新banner")
    public Result deletebanner(@PathVariable String bannerId){
        bannerService.removeById(bannerId);
        return Result.success().message("删除成功！");
    }

}

