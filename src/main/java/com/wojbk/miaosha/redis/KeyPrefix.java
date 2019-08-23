package com.wojbk.miaosha.redis;
/*
@date: 2019-5-10 19:45
@author: qinyonghao
@action: creat
 */
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
