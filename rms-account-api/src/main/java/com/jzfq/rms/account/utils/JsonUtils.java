package com.jzfq.rms.account.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class JsonUtils {
    public static <T> T fromJson(String input, Class<T> clazz) {
        return JSON.parseObject(input, clazz);
    }

    public static String toJSON(Object input) {
        if (input == null)
            return null;

        return JSON.toJSONString(input, SerializerFeature.PrettyFormat);
    }
}
