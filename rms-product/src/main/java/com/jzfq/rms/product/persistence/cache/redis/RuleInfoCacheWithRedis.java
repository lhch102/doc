package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.RuleClassification;
import com.jzfq.rms.product.bean.RuleInfo;
import com.jzfq.rms.product.persistence.cache.RuleInfoCache;

import java.util.List;
import java.util.Map;

/**
 * 1
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/1/31 9:28
 */
public class RuleInfoCacheWithRedis extends CacheWithRedis implements RuleInfoCache {

    private static final String RULEINFO_PREFIX = "rms_ruleinfo_prefix";

    @Override
    public void addRuleInfoByType(Map<String,Object> mapKey, String value) {
        String key = RULEINFO_PREFIX + mapKey.get("key");
        setCacheString(key, value);
    }

    @Override
    public List<RuleClassification> getRuleInfoByType(Integer tacticsType) {
        String key = RULEINFO_PREFIX + "_" + tacticsType;
        List<RuleClassification> ruleClassifications = JSON.parseObject(getCacheString(key), new TypeReference<List<RuleClassification>>() {
        });
        return ruleClassifications;
    }

    @Override
    public List<Map<String,Object>> getRuleInfoByCondition(Map conditionMap) {
        String key = RULEINFO_PREFIX + conditionMap.get("key");
        List<Map<String,Object>> value = JSON.parseObject(getCacheString(key), new TypeReference<List<Map<String,Object>>>() {
        });
        return value;

    }
}
