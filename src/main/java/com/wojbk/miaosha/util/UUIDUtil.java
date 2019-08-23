package com.wojbk.miaosha.util;

import java.util.UUID;
/**
 * 2019-5-15 21:50
 * @author qinyonghao
 */
public class UUIDUtil {
    public static String uuid(){
        //System.out.println(UUID.randomUUID().toString());
        return UUID.randomUUID().toString().replace("-","");
    }
}
