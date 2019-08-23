package com.wojbk.miaosha.redis;

public class OrderKet extends BasePrefix {
    public OrderKet(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
