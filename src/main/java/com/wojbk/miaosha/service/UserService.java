package com.wojbk.miaosha.service;


import com.wojbk.miaosha.dao.UserDao;
import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.exception.GlobalException;
import com.wojbk.miaosha.redis.RedisService;
import com.wojbk.miaosha.redis.UserKey;
import com.wojbk.miaosha.result.CodeMsg;
import com.wojbk.miaosha.result.Result;
import com.wojbk.miaosha.util.MD5Util;
import com.wojbk.miaosha.util.UUIDUtil;
import com.wojbk.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 2019-5-7 19:35
 *@author Qinyonghao
 */
@Service
public class UserService {
    public static final String COOKIE_NAME_TOKEN="token";

   @Autowired
    public  UserDao userDao;
   @Autowired
    RedisService redisService;


    public User getById(int id){
        return userDao.getById(id);
    }
    //登录操作
    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if(loginVo==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);//将所有的异常直接抛出
            //return CodeMsg.SERVER_ERROR;
        }
        String mobile=loginVo.getMobile();
        String password=loginVo.getPassword();
        User user=userDao.getById(Long.parseLong(mobile));
        if(user==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //密码校验
        String dbPassword=user.getPwd();
        String dbSalt=user.getSalt();
        String calPassword= MD5Util.formPassToDBPass(password,dbSalt);
        if(!calPassword.equals(dbPassword)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie,将cookie保存到客户端
        String token= UUIDUtil.uuid();
        addCookie(response,token,user);
        return true;
    }
    /**
     * @ 2019-5-15 22:00
     * @author qinyonghao
     *
     */
    //从缓存获取token
    public User getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user= redisService.get(UserKey.token,token,User.class);
        if(user!=null){
            //延长有效期
            addCookie(response,token,user);
        }
        return user;

    }

    public void addCookie(HttpServletResponse response,String token,User user){

        redisService.set(UserKey.token,token,user);
        Cookie cookie=new Cookie(COOKIE_NAME_TOKEN,token);
        //设置cookie的有效期，设置成与session的有效期一致
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");//网站的根目录
        response.addCookie(cookie);

    }
}
