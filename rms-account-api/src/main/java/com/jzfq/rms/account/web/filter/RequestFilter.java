package com.jzfq.rms.account.web.filter;

import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class RequestFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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
        if(request instanceof HttpServletRequest) {
            //将请求信息缓存一份 除文件上传 解决request 流只能被读取一次方案
            requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        }
        if (null == requestWrapper) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            if (menthod.equalsIgnoreCase("post")) {
                //排除验证码链接
                if (!requestURI.contains("getValidateCode.json")) {
                    //校验参数是否为空
                    if (RequestUtils.checkParams(requestWrapper.getReader(), response)) {
                        LOGGER.info("requestURI:[{}], requestExecuteTime:[{}]ms", requestURI, (System.currentTimeMillis() - beginTime));
                        return;
                    }
                }
                chain.doFilter(requestWrapper, servletResponse);
            } else {
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
