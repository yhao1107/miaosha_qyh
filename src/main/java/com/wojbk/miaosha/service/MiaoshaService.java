package com.wojbk.miaosha.service;


import com.wojbk.miaosha.entity.OrderInfo;
import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 2019-5-20 21:20
 * @author qinyong
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;


    @Transactional
    public OrderInfo miaosha(User user, GoodsVo goods) {
        //减库存
        goodsService.reduceStock(goods);
        System.out.println("减库存成功");
        //下订单（生成orderInfo和miaosha_order订单）
        return orderService.creatOrder(user,goods);

    }
}
