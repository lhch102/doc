package com.jzfq.rms.account.web.requestModel;

import com.jzfq.rms.account.common.Page;

public class BaseRequestModel extends Page{

    private String token; //请求令牌


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
