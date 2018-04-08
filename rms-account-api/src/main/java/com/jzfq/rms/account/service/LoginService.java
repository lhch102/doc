package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.AccountLogin;

public interface LoginService {
    public ResponseResult login(AccountLogin accountLogin);
}
