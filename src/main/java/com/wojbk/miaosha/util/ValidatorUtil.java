package com.wojbk.miaosha.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final Pattern mobilePattern=Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(src==null){
            return false;
        }
        Matcher m=mobilePattern.matcher(src);
        return m.matches();

    }
}
