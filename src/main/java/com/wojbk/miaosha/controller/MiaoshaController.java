package com.wojbk.miaosha.controller;

import com.wojbk.miaosha.entity.MiaoshaOrder;
import com.wojbk.miaosha.entity.OrderInfo;
import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.result.CodeMsg;
import com.wojbk.miaosha.service.GoodsService;
import com.wojbk.miaosha.service.MiaoshaService;
import com.wojbk.miaosha.service.OrderService;
import com.wojbk.miaosha.service.UserService;
import com.wojbk.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 2019-5-20 20:00
 * @author qinyonghao
 */

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model, @CookieValue(value = UserService.COOKIE_NAME_TOKEN,required=false)String cookieToken
            , @RequestParam(value = UserService.COOKIE_NAME_TOKEN,required=false)String paramToken, HttpServletResponse response
            , @RequestParam(value = "goodsId",defaultValue="0")long goodsId){
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }

        String param=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;

        User user=userService.getByToken(response,param);
        model.addAttribute("user",user);
        GoodsVo goods=goodsService.getGoodsVoByGoodsId(goodsId);

        //秒杀库存判断
        int stock=goods.getStockCount();

        if(stock<=0){
            model.addAttribute("errormsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "maiosha_fail";
        }
        //判断是否秒杀到

        MiaoshaOrder order=orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);

        if(order!=null){

            model.addAttribute("errormsg",CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存，下订单（生成订单）
        OrderInfo orderInfo=miaoshaService.miaosha(user,goods);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goods);
        System.out.println("成功");

        return "order_detail";
    }

}
