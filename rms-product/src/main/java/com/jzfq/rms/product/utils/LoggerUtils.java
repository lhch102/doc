package com.jzfq.rms.product.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class LoggerUtils {

    public static void trace(Class<?> clazz, String traceID, String messageTypeID,String message,String systemID) {
        MDC.put("traceId",traceID);
        MDC.put("messageTypeID",messageTypeID);
        MDC.put("systemID",systemID);
        Logger LOG = LoggerFactory.getLogger(clazz);
        // LOG.trace(message);
        LOG.debug(message);
    }
}
