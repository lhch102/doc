package com.jzfq.rms.account.web.filter;

import com.jzfq.rms.account.context.CallSystemIDThreadLocal;
import com.jzfq.rms.account.context.TraceIDThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class LoggerContextFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggerContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String traceID = request.getParameter("traceID");
        String callSystemID = request.getParameter("callSystemID");

        if (StringUtils.isNotBlank(traceID)) {
            MDC.put("traceID", traceID);
            TraceIDThreadLocal.setTraceID(traceID);
        }
        if (StringUtils.isNotBlank(callSystemID)) {
            MDC.put("callSystemID", callSystemID);
            CallSystemIDThreadLocal.setCallSystemID(callSystemID);
        }

        chain.doFilter(servletRequest, servletResponse);

        MDC.remove("traceID");
        MDC.remove("callSystemID");
        TraceIDThreadLocal.removeTraceID();
        CallSystemIDThreadLocal.removeCallSystemID();
    }

    @Override
    public void destroy() {

    }
}
