package com.wojbk.miaosha.redis;

/**
 * @ 2019-5-10 19:50
 * @author qinyonghao
 */
public class UserKey extends BasePrefix {

    private static final int TOKEN_EXPIRESECONDS=3600*24*2;
    private UserKey(int expireSeconds,String prefix) {
        super( expireSeconds,prefix);
    }
    public static UserKey token=new UserKey(TOKEN_EXPIRESECONDS,"tk");


}
