package com.jzfq.rms.account.common;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.service.impl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginCommon {

    private static final Logger log = LoggerFactory.getLogger(LoginCommon.class);

    private final String REDIS_ACCOUNT_LOGIN_USER = "account_login_user_";
    @Autowired
    private AccountUserService accountUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisOperations<String, String> redisOperations;

    /**
     * @return
     */
    public AccountUser getCurrentUser() {
        try {
            redisOperations = redisTemplate.opsForValue().getOperations();

            if (redisOperations != null) {
                String loginName = redisOperations.opsForValue().get(REDIS_ACCOUNT_LOGIN_USER);
                HashMap<String, Object> userMap = new HashMap<String, Object>();
                userMap.put("loginName", loginName);
                return accountUserService.queryByLoginName(userMap);
            } else {
                return null;
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
