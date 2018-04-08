package com.jzfq.rms.account.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.utils.RequestUtils;
import com.jzfq.rms.account.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class UserTokenInterceptor extends HandlerInterceptorAdapter {

    private final String REDIS_USER_KEY = "account_usertoken_";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String userName = request.getParameter("userName");
        RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
        RedisOperations<String, String> redisOperations = redisTemplate.opsForValue().getOperations();
        String userToken = redisOperations.opsForValue().get(REDIS_USER_KEY + userName);

        if (StringUtils.isBlank(userToken)) {
            return false;
        } else {
            return true;
        }
    }


}