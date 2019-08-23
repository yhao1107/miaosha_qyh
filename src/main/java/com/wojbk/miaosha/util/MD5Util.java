package com.wojbk.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.awt.*;

public class MD5Util {
    public static String md5(String src){

        return DigestUtils.md5Hex(src);
    }

    public static final String salt="1w2oj3b4k5";

    public static String inputPassFormPass(String inputPass){
        String str=""+salt.charAt(0)+salt.charAt(1)+inputPass+salt.charAt(4)+salt.charAt(5);
        return md5(str);
    }


    public static String formPassToDBPass(String formPass,String salt){
        String str=""+salt.charAt(0)+salt.charAt(1)+formPass+salt.charAt(4)+salt.charAt(5);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass,String salt){
        String formPass=inputPassFormPass(inputPass);
        String dbPass=formPassToDBPass(formPass,salt);
        return dbPass;
    }
    public static void main(String[] args){
        System.out.println(inputPassToDbPass("123456","1w2oj3b4k5"));
    }
}
