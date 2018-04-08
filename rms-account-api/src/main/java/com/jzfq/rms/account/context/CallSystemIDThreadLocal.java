package com.jzfq.rms.account.context;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class CallSystemIDThreadLocal {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置callSystemID
     * @param callSystemID callSystemID
     */
    public static void setCallSystemID(String callSystemID) {
        threadLocal.set(callSystemID);
    }

    /**
     * 获取callSystemID
     * @return callSystemID
     */
    public static String getCallSystemID() {
        return threadLocal.get();
    }

    /**
     * 删除callSystemID
     */
    public static void removeCallSystemID() {
        threadLocal.remove();
    }
}