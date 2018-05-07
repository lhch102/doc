package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.AccountLogin;
import com.jzfq.rms.account.common.AccountLoginResponse;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.service.LoginService;
import com.jzfq.rms.account.utils.MD5Util;
import com.jzfq.rms.account.utils.ObjectUtils;
import com.jzfq.rms.account.utils.RedisUtil;
import com.jzfq.rms.account.utils.SaltUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private AccountUserService accountUserService;
    @Autowired
    private RedisTemplate redisTemplate;

    private final static String REDIS_USER_KEY = "account_usertoken_";
    private final static String REDIS_USER_MODEL = "account_usermodel_";
    private final String REDIS_ACCOUNT_LOGIN_USER = "account_login_user_";

    public LoginServiceImpl() {
    }

    @Override
    public ResponseResult login(AccountLogin accountLogin) {
        ResponseResult responseResult = null;
        try {
            responseResult = new ResponseResult();
            responseResult.setCode(ReturnCode.FALED.code());

            if (accountLogin != null) {
                if (StringUtils.isBlank(accountLogin.getUserName())) {
                    responseResult.setCode(ReturnCode.NAME_EMPTY.code());
                    responseResult.setMsg(ReturnCode.NAME_EMPTY.msg());
                    return responseResult;
                }
                if (StringUtils.isBlank(accountLogin.getPassword())) {
                    responseResult.setCode(ReturnCode.PASSWORD_EMPTY.code());
                    responseResult.setMsg(ReturnCode.PASSWORD_EMPTY.msg());
                    return responseResult;
                }
                if (StringUtils.isBlank(accountLogin.getValidateCode())) {
                    responseResult.setCode(ReturnCode.VALIDAT_CODE_EMPTY.code());
                    responseResult.setMsg(ReturnCode.VALIDAT_CODE_EMPTY.msg());
                    return responseResult;
                }
                if (!accountLogin.getValidateCode().equalsIgnoreCase(accountLogin.getValidateCodeOld())) {
                    responseResult.setCode(ReturnCode.VALIDATE_CODE_FALSE.code());
                    responseResult.setMsg(ReturnCode.VALIDATE_CODE_FALSE.msg());
                    return responseResult;
                }
                //获取盐值
                String saltStr = SaltUtil.saltStr;
                log.info("用户登录盐值：" + saltStr);

                if (StringUtils.isNotBlank(accountLogin.getPassword())) {
                    String passwordMd5 = MD5Util.MD5(accountLogin.getPassword() + saltStr);
                    HashMap<String, Object> userMap = new HashMap<String, Object>();
                    userMap.put("name", accountLogin.getUserName());
                    userMap.put("password", passwordMd5);
                    log.info(String.format("用户登录用户名 %s,密码 %s", accountLogin.getUserName(), accountLogin.getPassword()));

                    AccountUser accountUser = accountUserService.queryByUserNameAndPassword(userMap);
                    if (accountUser != null) {
//                        String userTokenCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        //生成用户token ,并且存入redis
                        String userToken = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
                        RedisOperations<String, String> redisOperations = redisTemplate.opsForValue().getOperations();
                        redisOperations.opsForValue().set(userToken, accountLogin.getUserName());

                        redisTemplate.expire(userToken, 30, TimeUnit.SECONDS);
                        log.info(String.format("用户登录token=%s放入redis ,有效期30 分钟", userToken));
//                        //保存当前登录人信息
//                        redisOperations.opsForValue().set(REDIS_ACCOUNT_LOGIN_USER, accountLogin.getUserName());
//                        log.info(String.format("当前登录人 %s", accountLogin.getUserName()));

                        AccountLoginResponse accountLoginResponse = new AccountLoginResponse();
                        accountLoginResponse.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        accountLoginResponse.setToken(userToken);
                        accountLoginResponse.setUserName(accountUser.getLoginName());

                        responseResult.setCode(ReturnCode.SUCCESS.code());
                        responseResult.setMsg(ReturnCode.SUCCESS.msg());
                        responseResult.setData(accountLoginResponse);
                    } else {
                        log.info(String.format("用户登录失败，数据库未查到用户信息"));
                        responseResult.setCode(ReturnCode.NAME_PWD_FALSE.code());
                        responseResult.setMsg(ReturnCode.NAME_PWD_FALSE.msg());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResult;
    }
}



