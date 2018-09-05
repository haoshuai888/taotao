package com.taotao.sso.service.impl;


import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public  class UserServiceImpl implements UserService{
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TaotaoResult checkData(String param,int type) {
        if(type==1){
            TbUser tbUser=tbUserMapper.getUserByUserName(param);

            if(tbUser != null){
                return TaotaoResult.ok(false);
            }
        }else if(type == 2){
            TbUser tbUser =tbUserMapper.getUserByPhone(param);
            if(tbUser !=null){
                return TaotaoResult.ok(false);
            }
        }else if(type == 3){
            TbUser tbUser = tbUserMapper.getUserByEmail(param);
            if(tbUser != null){
                return TaotaoResult.ok(false);
            }
        }
    else
        {
            return TaotaoResult.build(400,"ok","传入的参数类型不合法");
        }
    return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        // 1、使用TbUser接收提交的请求。

        if (StringUtils.isBlank(user.getUserName())) {
            return TaotaoResult.build(400, "用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassWord())) {
            return TaotaoResult.build(400, "密码不能为空");
        }

        TaotaoResult result = checkData(user.getUserName(), 1);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "此用户名已经被使用");
        }
        //校验电话是否可以
        if (StringUtils.isNotBlank(user.getPhone())) {
            result = checkData(user.getPhone(), 2);
            if (!(boolean) result.getData()) {
                return TaotaoResult.build(400, "此手机号已经被使用");
            }
        }
        //校验email是否可用
        if (StringUtils.isNotBlank(user.getEmail())) {
            result = checkData(user.getEmail(), 3);
            if (!(boolean) result.getData()) {
                return TaotaoResult.build(400, "此邮件地址已经被使用");
            }
        }
        // 2、补全TbUser其他属性。
        user.setCreated(new Date());
        user.setUpdated(new Date());
        // 3、密码要进行MD5加密。
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassWord().getBytes());
        user.setPassWord(md5Pass);
        // 4、把用户信息插入到数据库中。
        tbUserMapper.insert(user);
        // 5、返回TaotaoResult。
        return TaotaoResult.ok();
    }


}
