package com.wojbk.miaosha.controller;


import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.redis.RedisService;
import com.wojbk.miaosha.redis.UserKey;
import com.wojbk.miaosha.result.CodeMsg;
import com.wojbk.miaosha.result.Result;
import com.wojbk.miaosha.service.UserService;
import com.wojbk.miaosha.util.ValidatorUtil;
import com.wojbk.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 *@date: 2019-5-11 19:15
 *@author: Qinyonghao
 *@action: creat
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log=LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @RequestMapping("/to_login")
    public String toLogin(){
       return "login";

    }


    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());

        //参数校验
//        String mobile=loginVo.getMobile();
//        String password=loginVo.getPassword();
//        if(StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
//
//        if(StringUtils.isEmpty(password)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
        //登录
        userService.login(response,loginVo);
        return Result.success(true);

    }


}
