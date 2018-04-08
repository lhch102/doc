package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.AccountLogin;
import com.jzfq.rms.account.common.AccountLoginResponse;
import com.jzfq.rms.account.enums.EnumLogin;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    private final String REDIS_USER_KEY = "account_usertoken_";

    public LoginServiceImpl() {
    }

    @Override
    public ResponseResult login(AccountLogin accountLogin) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(EnumLogin.FALED.getCode());

        if (accountLogin != null) {
            if (StringUtils.isBlank(accountLogin.getUserName())) {
                responseResult.setCode(EnumLogin.NAME_EMPTY.getCode());
            }
            if (StringUtils.isBlank(accountLogin.getPassword())) {
                responseResult.setCode(EnumLogin.PASSWORD_EMPTY.getCode());
            }
            if (StringUtils.isBlank(accountLogin.getValidateCode())) {

            }

            if (!accountLogin.getValidateCode().equalsIgnoreCase(accountLogin.getValidateCodeOld())) {
                responseResult.setCode(EnumLogin.VALIDATE_CODE_FALSE.getCode());
            }
            HashMap<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("name", accountLogin.getUserName());
            userMap.put("password", accountLogin.getPassword());
            AccountUser accountUser = accountUserService.queryByUserNameAndPassword(userMap);

            if (accountUser != null) {
                //生成用户token ,并且存入redis
                String userToken = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
                RedisOperations<String, String> redisOperations = redisTemplate.opsForValue().getOperations();
                redisOperations.opsForValue().set(REDIS_USER_KEY + accountLogin.getUserName(), userToken);
                redisTemplate.expire(REDIS_USER_KEY + accountLogin.getUserName(), 30, TimeUnit.SECONDS);


                AccountLoginResponse accountLoginResponse = new AccountLoginResponse();
                accountLoginResponse.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                accountLoginResponse.setToken(userToken);
                accountLoginResponse.setUserName(accountUser.getName());


                responseResult.setCode(EnumLogin.SUCCESS.getCode());
                responseResult.setMsg(EnumLogin.SUCCESS.getMessage());
                responseResult.setData(accountUser);
            }
        }
        return responseResult;
    }
}



