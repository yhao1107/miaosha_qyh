package com.wojbk.miaosha.controller;


import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.service.GoodsService;
import com.wojbk.miaosha.service.UserService;
import com.wojbk.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ 2019-5-15 22:00
 * @author qinyonghao
 *
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(Model model, @CookieValue(value = UserService.COOKIE_NAME_TOKEN,required=false)String cookieToken
    , @RequestParam(value = UserService.COOKIE_NAME_TOKEN,required=false)String paramToken, HttpServletResponse response){
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String param=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;

        User user=userService.getByToken(response,param);
        model.addAttribute("user",user);
        List<GoodsVo> goodsList=goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);

        return "goods_list";
    }
}
