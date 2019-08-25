package com.wojbk.miaosha.service;


import com.wojbk.miaosha.dao.OrderDao;
import com.wojbk.miaosha.entity.MiaoshaOrder;
import com.wojbk.miaosha.entity.OrderInfo;
import com.wojbk.miaosha.entity.User;
import com.wojbk.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 2019-5-20 20:30
 * @author qinyong
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId,long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
    }

    @Transactional
    public OrderInfo creatOrder(User user, GoodsVo goods) {
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsCount(1);
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setUserId(user.getId());
        orderInfo.setOrderStatus(0);
        orderInfo.setOrderChannel(1);
        long orderId=orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder=new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setOrderId(orderId);
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;

    }


}
