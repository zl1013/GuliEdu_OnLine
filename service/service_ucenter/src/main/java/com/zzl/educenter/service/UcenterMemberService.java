package com.zzl.educenter.service;

import com.zzl.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzl.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-05
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录并返回token
    String login(UcenterMember ucenterMember);

    //注册
    boolean regist(RegisterVo registerVo);

    boolean registed(String mobile);

    //根据openid查询用户
    UcenterMember getMemberByOpenId(String openid);
}
