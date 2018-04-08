package com.jzfq.rms.account.persistence.cache;


import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public interface ICache {

    void setCacheString(String key, String value);

    String getCacheString(String key);

    <T> ValueOperations<String, T> setCacheObject(String key, T value);

    <T> T getCacheObject(String key);

    <T> ListOperations<String, T> setCacheList(String key, List<T> dataList);

    <T> List<T> getCacheList(String key);

    <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap);

    <T> Map<Object, Object> getCacheMap(String key);
}
