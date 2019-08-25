package com.wojbk.miaosha.service;


import com.wojbk.miaosha.dao.GoodsDao;
import com.wojbk.miaosha.entity.Goods;
import com.wojbk.miaosha.entity.MiaoshaGoods;
import com.wojbk.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2019-5-16 20:30
 * @author qinyonghao
 */

@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;
    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }


    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g=new MiaoshaGoods();
        System.out.println("减库存操作");
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
