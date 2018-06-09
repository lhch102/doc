package com.jzfq.rms.product.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.product.enums.ReturnCode;
import com.jzfq.rms.product.utils.PropertiesUtil;
import com.jzfq.rms.product.utils.RequestUtils;
import com.jzfq.rms.product.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class RequestFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    private List<String> excludeUrls = new ArrayList<>();

    private final String REDIS_ACCOUNT_LOGIN_USER = "account_login_user_";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrls.add("getValidateCode.json");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        long beginTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String menthod = request.getMethod();
        String requestURI = request.getRequestURI();
        //记录请求参数
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("requestURI:[{}], requestData:[{}]", requestURI, RequestUtils.getRequestData(request));
        }
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            //将请求信息缓存一份 除文件上传 解决request 流只能被读取一次方案
            requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        }

        if (null == requestWrapper) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            String token = null;
            if (menthod.equalsIgnoreCase("post")) {
                BufferedReader reader = requestWrapper.getReader();
                JSONObject paramJson = RequestUtils.getParams(reader);
                if (!paramJson.isEmpty()) {
                    token = paramJson.getString("token");
                }
                //排除验证码链接
                if (!excludeUrls.contains(requestURI)) {
                    //校验参数是否为空并保存请求参数
                    if (RequestUtils.checkParams(paramJson, response, requestURI, menthod)) {
                        LOGGER.info("requestURI:[{}], requestExecuteTime:[{}]ms", requestURI, (System.currentTimeMillis() - beginTime));
                        return;
                    }
                }
                chain.doFilter(requestWrapper, servletResponse);
            } else {
                token = request.getParameter("token");
                chain.doFilter(requestWrapper, servletResponse);
            }
        }

        //记录请求执行时长
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("requestURI:[{}], requestExecuteTime:[{}]ms", requestURI, (System.currentTimeMillis() - beginTime));
        }
    }

    @Override
    public void destroy() {

    }

}
