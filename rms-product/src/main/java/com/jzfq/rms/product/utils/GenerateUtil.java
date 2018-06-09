package com.jzfq.rms.product.utils;

/**
 * 生成策略编号工具类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/3/19 15:38
 */
public class GenerateUtil {

    private static int i = 0;

    /**
     * 在初始值上自增1
     *
     * @return
     */
    public static String plus() {
        i++;
        String s = "00000" + i;
        return s;
    }
}
