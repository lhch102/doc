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
import com.jzfq.rms.account.service.impl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:43
 */
@RestController
@RequestMapping(value = "/account")
public class AccountLoginAction {

    private final String REDIS_ACCOUNT_LOGIN_USER = "account_login_user_";
    private static final Logger log = LoggerFactory.getLogger(AccountLoginAction.class);

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult queryUserList(@RequestBody JSONObject param, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        //String userName, String passWord, String validateCode

        log.info(String.format("请求参数 %s", param));
        String userName = param.getString("userName");
        String passWord = param.getString("passWord");
        String validateCode = param.getString("validateCode");

        //session 获取当前登录验证码
        String automaticValidateCode = (String) request.getSession().getAttribute("validateCode");

        request.getSession().setAttribute(REDIS_ACCOUNT_LOGIN_USER, userName);

        AccountLogin accountLogin = new AccountLogin();
        accountLogin.setUserName(userName);
        accountLogin.setPassword(passWord);
        accountLogin.setValidateCode(validateCode);
        accountLogin.setValidateCodeOld(automaticValidateCode);

        ResponseResult responseResult = loginService.login(accountLogin);
        //登录成功清空code
        request.getSession().setAttribute("validateCode", "");
        return responseResult;
    }
}
