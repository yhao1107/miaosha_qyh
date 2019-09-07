package com.wojbk.miaosha.controller;


import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.redis.GoodsKey;
import com.wojbk.miaosha.redis.RedisService;
import com.wojbk.miaosha.service.GoodsService;
import com.wojbk.miaosha.service.UserService;
import com.wojbk.miaosha.vo.GoodsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    RedisService redisService;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    ApplicationContext applicationContext;
    //商品列表
    @RequestMapping(value="/to_list",produces = "text/html")
    @ResponseBody
    public String toList(Model model, @CookieValue(value = UserService.COOKIE_NAME_TOKEN,required=false)String cookieToken
    , @RequestParam(value = UserService.COOKIE_NAME_TOKEN,required=false)String paramToken, HttpServletResponse response, HttpServletRequest request){
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String param=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;

        User user=userService.getByToken(response,param);
        model.addAttribute("user",user);
        List<GoodsVo> goodsList=goodsService.listGoodsVo();


        model.addAttribute("goodsList",goodsList);

        //return "goods_list";
        //取缓存
        String html=redisService.get(GoodsKey.getGoodsList,"",String.class);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        SpringWebContext ctx=new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),  applicationContext);
        //手动渲染
        html=thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        if(!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList,"gl",html);
        }
        return html;


    }
    //商品详情页
    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model, @CookieValue(value = UserService.COOKIE_NAME_TOKEN,required=false)String cookieToken
            , @RequestParam(value = UserService.COOKIE_NAME_TOKEN,required=false)String paramToken, HttpServletResponse response
             , @PathVariable("goodsId")long goodsId){
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String param=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        User user=userService.getByToken(response,param);
        model.addAttribute("user",user);
        GoodsVo goods=goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);
        //秒伤状态
        long startAt=goods.getStartDate().getTime();
        long endAt=goods.getEndDate().getTime();
        long now=System.currentTimeMillis();
        int miaoshaStatus=0;
        int remainSeconds=0;
        if(now<startAt){//秒杀还没开始
            miaoshaStatus=0;
            remainSeconds=(int)((startAt-now)/1000);
        }else if(now>endAt){//秒杀已结束
            miaoshaStatus=2;
            remainSeconds=-1;
        }else {//秒杀正在进行
            miaoshaStatus=1;
            remainSeconds=0;

        }
        //System.out.println("成功");
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        return "goods_detail";
    }


}
