package org.seckill.vo;

import org.hibernate.validator.constraints.Length;
import org.seckill.validator.MobileValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginVo {
    @NotNull
    @MobileValidator
    private String mobile;

    @NotBlank(message = "密码为必填项")
    @Length(min = 6, message = "密码至少6位")
    private String password;

    public LoginVo() {
    }

    public LoginVo(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

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
