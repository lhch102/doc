package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.Dictionary;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.IDictionaryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public class DictionaryCacheWithRedis extends CacheWithRedis implements IDictionaryCache {

    private final Logger LOG = LoggerFactory.getLogger(DictionaryCacheWithRedis.class);

    private static final String DICTIONARY_PREFIX = "dictionary_prefix";

    @Override
    public Dictionary getDictionaryValueByCondition(Map conditionMap) throws BusinessException {
        String key = DICTIONARY_PREFIX + "_" + conditionMap.get("type")
                + "_" + conditionMap.get("rms_key");
        Dictionary value = JSON.parseObject(getCacheString(key), new TypeReference<Dictionary>() {
        });
        return value;

    }

    @Override
    public void addDictionary(Dictionary record) {

        String key = DICTIONARY_PREFIX + "_" + record.getType()
                + "_" + record.getRms_key();
        String value = JSON.toJSONString(record);
        setCacheString(key, value);
    }

    @Override
    public String getDictionaryValue(Map conditionMap) throws BusinessException {
        String key = DICTIONARY_PREFIX + "_" + conditionMap.get("type")
                + "_" + conditionMap.get("rms_key");
        String value = getCacheString(key);
        return value;

    }

    @Override
    public void addDictionaryValue(Dictionary record) {

        String key = DICTIONARY_PREFIX + "_" + record.getType()
                + "_" + record.getRms_key();
        String value = record.getRms_value();
        setCacheString(key, value);
    }

    @Override
    public void addDictionaryByType(String type,String value) {
        String key = DICTIONARY_PREFIX + "_" + type;
        setCacheString(key, value);
    }

    @Override
    public String getDictionaryByType(String type) {
        String key = DICTIONARY_PREFIX + "_" + type;
        return getCacheString(key);
    }

}
