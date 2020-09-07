package com.zzl.educms.service;

import com.zzl.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-04
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询首页banner
    List<CrmBanner> banner();
}
