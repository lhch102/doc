package com.jzfq.rms.product.context;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class TraceIDThreadLocal {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置traceID
     * @param traceID traceID
     */
    public static void setTraceID(String traceID) {
        threadLocal.set(traceID);
    }

    /**
     * 获取traceID
     * @return traceID
     */
    public static String getTraceID() {
        return threadLocal.get();
    }

    /**
     * 删除traceID
     */
    public static void removeTraceID() {
        threadLocal.remove();
    }
}