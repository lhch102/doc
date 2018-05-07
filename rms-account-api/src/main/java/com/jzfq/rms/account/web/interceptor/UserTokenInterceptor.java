package com.jzfq.rms.account.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.utils.RequestUtils;
import com.jzfq.rms.account.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class UserTokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserTokenInterceptor.class);

    private final String REDIS_USER_KEY = "account_usertoken_";
    private final String REDIS_ACCOUNT_LOGIN_USER = "account_login_user_";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = request.getParameter("token");
        BufferedReader reader = request.getReader();
        JSONObject paramJson = RequestUtils.getParams(reader);
        if (!paramJson.isEmpty()) {
            if (StringUtils.isBlank(token)) {
                token = paramJson.getString("token");
            }
        }
        if (StringUtils.isBlank(token)) {
            //token验证失败
            log.info(String.format("未获取到token信息 %s", token));
            return this.setResponse(response, ReturnCode.EMPTY_TOKEN);
        } else {
            RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
            RedisOperations<String, String> redisOperations = redisTemplate.opsForValue().getOperations();
            String userName = redisOperations.opsForValue().get(token);

            log.info(String.format("token信息 %s  ,用户姓名 %s", token, userName));

            if (StringUtils.isNotBlank(userName)) {
                //保存当前登录人信息
                redisOperations.opsForValue().set(REDIS_ACCOUNT_LOGIN_USER, userName);
                log.info(String.format("当前登录人 %s", userName));
                return true;
            } else {
                log.info("未获取到userName信息");
                return this.setResponse(response, ReturnCode.TOKEN_OVERDUE);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }

    private boolean setResponse(HttpServletResponse response, ReturnCode returnCode) throws IOException {
        //token验证失败
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            ResponseResult responseResult = new ResponseResult();
            responseResult.setData("");
            responseResult.setMsg(returnCode.msg());
            responseResult.setCode(returnCode.code());
            out = response.getWriter();
            out.append(JSON.toJSONString(responseResult));
            return false;
        } catch (Exception e) {
            response.sendError(500);
            e.printStackTrace();
            return false;
        }
    }
}