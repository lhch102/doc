package com.jzfq.rms.product.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 参数有效性检查
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class ValidationUtils {

    /**
     * 是否空值检查
     * @param params
     * @param notEmptyArgs
     * @return
     */
    public static List<String> emptyValidate(Map<String, Object> params, String[] notEmptyArgs) {
        List<String> illegalArgs = new ArrayList<String>(notEmptyArgs.length);
        for (String arg : notEmptyArgs) {
            Object paraValue = params.get(arg);
            if (paraValue == null || paraValue.equals("")) {
                illegalArgs.add(arg);
            }
        }
        return illegalArgs;
    }

    /**
     * 是否数字检查
     * @param params
     * @param numbericArgs
     * @return
     */
    public static List<String> numericValidate(Map<String, Object> params, String[] numbericArgs) {
        List<String> illegalArgs = new ArrayList<String>(numbericArgs.length);
        for (String arg : numbericArgs) {
            Object paraValue = params.get(arg);
            if (paraValue != null && !StringUtils.isNumeric(paraValue.toString())) {
                illegalArgs.add(arg);
            }
        }
        return illegalArgs;
    }

}
