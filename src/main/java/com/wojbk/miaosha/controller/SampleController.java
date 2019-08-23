package com.wojbk.miaosha.controller;


import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.redis.RedisService;
import com.wojbk.miaosha.redis.UserKey;
import com.wojbk.miaosha.result.CodeMsg;
import com.wojbk.miaosha.result.Result;
import com.wojbk.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 @date: 2019-5-6 19:15
 @author: Qinyonghao
 @action: creat
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
       model.addAttribute("name","qinyong");
       return "hello";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> success_hello(){

        return Result.success("qinyonghao");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error_hello(){

        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbget(){
        User user=userService.getById(1);
        return Result.success(user);
    }
//
//    @RequestMapping("/db/tx")
//    @ResponseBody
//    public Result<Boolean> tx(){
//
//        return Result.success(userService.tx());
////    }
//    @RequestMapping("/redis/get")
//    @ResponseBody
//    public Result<User> redisget(){
//        User v1=redisService.get(UserKey.getById,""+2,User.class);//key:"UserKey:id1"
//        return Result.success(v1);
//    }

//    @RequestMapping("/redis/set")
//    @ResponseBody
//    public Result<Boolean> redisset(){
//        User user=new User();
//        user.setId(5);
//        user.setName("qin");
//        redisService.set(UserKey.getById,""+2,user);
//
//        return Result.success(true);
//    }
}
