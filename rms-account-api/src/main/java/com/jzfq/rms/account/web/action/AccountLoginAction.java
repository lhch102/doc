package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.AccountLogin;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:43
 */
@RestController
@RequestMapping(value = "/accountLogin")
public class AccountLoginAction {

    @Autowired
    private LoginService loginService;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult queryUserList(String userName, String passWord, String validateCode, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {


        //session 获取当前登录验证码
        String automaticValidateCode = (String) request.getSession().getAttribute("validateCode");

        AccountLogin accountLogin = new AccountLogin();
        accountLogin.setUserName(userName);
        accountLogin.setPassword(passWord);
        accountLogin.setValidateCode(validateCode);
        accountLogin.setValidateCodeOld(automaticValidateCode);




        ResponseResult responseResult = loginService.login(accountLogin);
        return responseResult;
    }
}
