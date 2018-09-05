package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author haoshuai
 * @Title: UserController
 * @ProjectName taotao-parent
 * @Description: TODO
 * @date 2018/9/59:18
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param, @PathVariable Integer type) {
        TaotaoResult taotaoResult = userService.checkData(param, type);
        return taotaoResult;
    }

    @RequestMapping(value="/user/register", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user ) {
        TaotaoResult result = userService.createUser(user);
        return result;
    }


}
