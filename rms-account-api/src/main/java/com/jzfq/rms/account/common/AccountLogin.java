package com.jzfq.rms.account.common;

public class AccountLogin {
    private String userName;
    private String password;
    private String token;
    private String validateCode;
    private String validateCodeOld;
    private String loginTime;//登录时间

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getValidateCodeOld() {
        return validateCodeOld;
    }

    public void setValidateCodeOld(String validateCodeOld) {
        this.validateCodeOld = validateCodeOld;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
