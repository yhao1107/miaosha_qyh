package com.wojbk.miaosha.dao;

import com.wojbk.miaosha.entity.MiaoshaOrder;
import com.wojbk.miaosha.entity.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 2019-2-20 20:50
 * @author qinyonghao
 */
@Mapper

public interface OrderDao {

    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId,@Param("goodsId")long goodsId);

    @Insert("insert into miaosha_order(user_id,order_id,goods_id)value(#{userId},#{orderId},#{goodsId})")
    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
    @Insert("insert into order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channel,order_status,create_date)value" +
            "(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{orderStatus},#{createDate})")
    long insert(OrderInfo orderInfo);
}
