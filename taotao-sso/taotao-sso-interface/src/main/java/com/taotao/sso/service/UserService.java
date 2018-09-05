package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @author haoshuai
 * @Title: UserService
 * @ProjectName taotao-parent
 * @Description: TODO
 * @date 2018/9/414:26
 */
public interface UserService {
    /**
     * 检查数据是否可用
     * @param param 检查的数据
     * @param type 数据类型 1：用户名 2：手机 3，email
     * @return status:200 成功 ； msg:ok ; data:true/false1
     */
    TaotaoResult checkData(String param,int type);

    /**
     * 用TbUser接受提交的请求数据
     * @param tbUser
     * @return
     */
    TaotaoResult createUser(TbUser tbUser);

    /**
     * 数据添加到数据库。
     * @param tbUSer
     */
    Void insert (TbUser tbUSer);
}
