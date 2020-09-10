package com.zzl.ucenter.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.commonutils.JwtUtils;
import com.zzl.ucenter.entity.UcenterMember;
import com.zzl.ucenter.entity.vo.RegisterVo;
import com.zzl.ucenter.mapper.UcenterMemberMapper;
import com.zzl.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzl.servicebase.config.exceptionhandler.LangJuException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 乍暖还寒
 * @since 2020-09-05
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new LangJuException(20001,"用户名密码不合法");
        }
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        if (member == null){
            throw new LangJuException(20001,"用户不存在");
        }
        if (!SecureUtil.md5(password).equals(member.getPassword())){
            throw new LangJuException(20001,"密码不正确");
        }
        if ( member.getIsDisabled().equals("1")){
            throw new LangJuException(20001,"用户被禁止登录");
        }
        return JwtUtils.getJwtToken(member.getId(),member.getNickname());
    }

    @Override
    public boolean regist(RegisterVo registerVo) {
        UcenterMember ucenterMember = new UcenterMember();
        BeanUtils.copyProperties(registerVo,ucenterMember);
        ucenterMember.setPassword(SecureUtil.md5(registerVo.getPassword()));
        int insert = baseMapper.insert(ucenterMember);
        if (insert == 1){
            return true;
        }
        return false;
    }

    //返回true表示该手机号已被注册
    @Override
    public boolean registed(String mobile) {
        System.out.println(mobile);
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        int count = baseMapper.selectCount(queryWrapper);
        System.out.println(count);
        if (count == 0){
            return false;
        }
        return true;
    }

    @Override
    public UcenterMember getMemberByOpenId(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }


}

