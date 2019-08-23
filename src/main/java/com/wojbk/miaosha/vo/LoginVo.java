package com.wojbk.miaosha.vo;

import com.wojbk.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
/**
 * @date: 2019-5-12 18:15
 *  @author: Qinyonghao
 *  @action: creat
 */
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;


    @NotNull
    @Length(min =16)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
