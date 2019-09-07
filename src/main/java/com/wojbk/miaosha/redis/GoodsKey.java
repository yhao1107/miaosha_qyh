package com.wojbk.miaosha.redis;

/**
 * @ 2019-9-7 19:50
 * @author qinyonghao
 */
public class GoodsKey extends BasePrefix {


    private GoodsKey(int expireSeconds,String prefix) {
        super( expireSeconds,prefix);
    }
    public static GoodsKey getGoodsList=new GoodsKey(60,"gl");


}
