package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.CustomerType;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.IMutilpleCache;
import com.jzfq.rms.product.persistence.cache.ITacticsCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public class TacticsCacheWithRedis extends CacheWithRedis implements ITacticsCache {

    private final Logger LOG = LoggerFactory.getLogger(TacticsCacheWithRedis.class);

    private static final String RISK_TACTICS_PREFIX = "risk_tactics_prefix_";
    private static final String RISK_TACTICS_PREFIX_KQ = "risk_tacticsKQ_prefix_";
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public TacticsRecord getTacticsByTacticsID(String tacticsID) throws BusinessException {
        String key = RISK_TACTICS_PREFIX + tacticsID;
        LOG.info("redis_key>>>>>>>>>>>>>>>>>>>>>>>"+key);
        TacticsRecord value = JSON.parseObject(getCacheString(key), new TypeReference<TacticsRecord>() {
        });
        return value;
    }

    @Override
    public void setTacticsCache(TacticsRecord record) {
        String key = RISK_TACTICS_PREFIX + record.getTacticsId();
        String value = JSON.toJSONString(record);
        setCacheString(key, value);
    }

    @Override
    public void setTacticsCache(CustomerType customerType) {
        String key = RISK_TACTICS_PREFIX_KQ + customerType.getCustomerTypeId();
        String value = JSON.toJSONString(customerType);
        setCacheString(key, value);
    }

    @Override
    public CustomerType getTacticsCache(Integer customerTypeId) {
        String key = RISK_TACTICS_PREFIX_KQ + customerTypeId;
        CustomerType value = JSON.parseObject(getCacheString(key), new TypeReference<CustomerType>() {
        });
        return value;
    }

}
