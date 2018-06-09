package com.jzfq.rms.product.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.enums.ReturnCode;
import com.jzfq.rms.product.utils.PropertiesUtil;
import com.jzfq.rms.product.utils.RequestUtils;
import com.jzfq.rms.product.utils.SpringContextHolder;
import com.jzfq.rms.product.web.filter.BodyReaderHttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@Component
public class UserTokenInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserTokenInterceptor.class);

    private final String REDIS_USER_KEY = "account_usertoken_";
    private final String REDIS_ACCOUNT_LOGIN_USER = "account_login_user_";

    @Value("${redirectUrl}")
    private String redirectUrl;
    @Value("${sysUri}")
    private String sysUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = "";
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            //将请求信息缓存一份 除文件上传 解决request 流只能被读取一次方案
            requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        }

        String method = request.getMethod();
        if (method.equalsIgnoreCase("get")) {
            token = request.getParameter("token");
        } else {
            BufferedReader reader = requestWrapper.getReader();
            JSONObject paramJson = RequestUtils.getParams(reader);
            if (!paramJson.isEmpty()) {
                token = paramJson.getString("token");
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("redirection",redirectUrl);
        map.put("sysUri",sysUri);
        if (StringUtils.isBlank(token)) {
            //token验证失败
            log.info(String.format("未获取到token信息 %s", token));
            // response.setStatus(302);
            // res  ponse.setHeader("location",PropertiesUtil.getString("redirectUrl"));

            return this.setResponse(response, ReturnCode.REDIRECT,map);
        } else {
            RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
            RedisOperations<String, String> redisOperations = redisTemplate.opsForValue().getOperations();
            JSONObject object = (JSONObject) JSONObject.parse(redisOperations.opsForValue().get(token));
            if (object == null){
                // response.sendRedirect(PropertiesUtil.getString("redirectUrl").toString());
                return this.setResponse(response, ReturnCode.REDIRECT,map);
            }
            String userName= object.getString("loginName");
            log.info(String.format("token信息 %s  ,用户姓名 %s", token, userName));

            if (StringUtils.isNotBlank(userName)) {
                //保存当前登录人信息
                redisOperations.opsForValue().set(REDIS_ACCOUNT_LOGIN_USER, userName);
                log.info(String.format("当前登录人 %s", userName));
                return true;
            } else {
                log.info("未获取到userName信息");
                // response.sendRedirect(PropertiesUtil.getString("redirectUrl"));
                return this.setResponse(response, ReturnCode.REDIRECT,map);
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean setResponse(HttpServletResponse response, ReturnCode returnCode,Map<String,Object> map) throws IOException {
        //token验证失败
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            ResponseResult responseResult = new ResponseResult();

            responseResult.setData(map);
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