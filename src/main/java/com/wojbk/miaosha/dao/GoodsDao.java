package com.wojbk.miaosha.dao;

import com.wojbk.miaosha.entity.Goods;
import com.wojbk.miaosha.entity.MiaoshaGoods;
import com.wojbk.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 2019-5-16 20:30
 * @author qinyonghao
 */
@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join goods g on mg.goods_id=g.id")
    List<GoodsVo> listGoodsVo();
    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join goods g " +
            "on mg.goods_id=g.id where mg.id=#{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count=stock_count-1 where goods_id=#{goodsId} and stock_count>0")
    void reduceStock(MiaoshaGoods goods);
//    @Update("update miaosha_goods set stock_count=stock_count-1 where goods_id=#{goodsId} and stock_count>0")
//    void reduceStock(MiaoshaGoods goods);
}
