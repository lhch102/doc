package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.product.bean.DictionaryExample;
import com.jzfq.rms.product.persistence.dao.IDictionaryDAO;
import com.jzfq.rms.product.service.ITacticsRecordService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml"})
public class CacheWithRedisTest {

    @Autowired
    private IDictionaryDAO dictionaryDAO;
    @Autowired
    private DictionaryCacheWithRedis cacheWithRedis;

    @Autowired
    private ITacticsRecordService service;

    public void test1() {
        String type = "jd_merchant_type";
        DictionaryExample example = new DictionaryExample();
        DictionaryExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        String dictionaryByType = cacheWithRedis.getDictionaryByType(type);
        List<Map<String, Object>> dicList;
        if (StringUtils.isBlank(dictionaryByType)) {
            dicList = dictionaryDAO.getDictionary(example);
            if (dicList != null) {
                cacheWithRedis.addDictionaryByType(type, JSONUtils.toJSONString(dicList));
            }
        } else {
            dicList = (List<Map<String, Object>>) JSONUtils.parse(dictionaryByType);
        }
        System.out.println(dicList);
    }

    public void test2() {
        int i = 2;
        switch (i) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("default");
                break;
        }
    }



}