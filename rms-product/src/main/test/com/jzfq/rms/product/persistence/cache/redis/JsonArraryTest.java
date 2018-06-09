package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import java.lang.reflect.Type;

import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/19 17:33
 */
public class JsonArraryTest {


    @Test
    public void test1() {
        int[][][] array = {{{1,2},{1,}},{{3},{2}}};
        //将array数组转换成字符串
        String arraystr =JSONObject.toJSONString(array, SerializerFeature.WriteClassName);
        System.out.println(arraystr);

        List<String> d = JSON.parseArray(arraystr, String.class);

    }



}
