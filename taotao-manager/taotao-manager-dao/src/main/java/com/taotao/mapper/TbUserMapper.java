package com.taotao.mapper;

import com.taotao.pojo.TbUser;

public interface TbUserMapper {

    /**
     * 根据用户名查询
     * @param userName
     * @return  result不为null，对象存在。
     */
     public TbUser getUserByUserName(String userName);

     public TbUser getUserByPhone(String phone);
     public TbUser getUserByEmail(String email);

    /**
     * 添加一个用户到数据库
     * @param tbUSer
     */
    void insert (TbUser tbUSer);
}





























