package com.jzfq.rms.account.persistence.cache.redis;

import com.jzfq.rms.account.persistence.cache.ICache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CacheWithRedis implements ICache {

	private final Logger LOG = LoggerFactory.getLogger(CacheWithRedis.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	/**
	 * 缓存基本的对象，Integer、String
	 *
	 * @param key   缓存的键值
	 * @param value 缓存的值
	 * @return 缓存的对象
	 */
	@Override
	public void setCacheString(String key, String value) {
		ValueOperations<String, String> operation = redisTemplate.opsForValue();
		operation.set(key, value);
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key 缓存键值
	 * @return 缓存键值对应的数据
	 */
	@Override
	public String getCacheString(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key   缓存的键值
	 * @param value 缓存的值
	 * @return 缓存的对象
	 */
	@Override
	public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
		ValueOperations<String, String> operation = redisTemplate.opsForValue();
		operation.set(key, (String) value);
		return (ValueOperations<String, T>) operation;
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key 缓存键值
	 * @return 缓存键值对应的数据
	 */
	@Override
	public <T> T getCacheObject(String key) {
		ValueOperations<String, T> operation = (ValueOperations<String, T>) redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 缓存List数据
	 *
	 * @param key      缓存的键值
	 * @param dataList 待缓存的List数据
	 * @return 缓存的对象
	 */
	@Override
	public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
		ListOperations listOperation = redisTemplate.opsForList();
		if (null != dataList) {
			int size = dataList.size();
			for (int i = 0; i < size; i++) {
				listOperation.leftPush(key, dataList.get(i));
			}
		}
		return listOperation;
	}

	/**
	 * 获得缓存的list对象
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	@Override
	public <T> List<T> getCacheList(String key) {
		List<T> dataList = new ArrayList<T>();
		ListOperations<String, String> listOperation = redisTemplate.opsForList();
		Long size = listOperation.size(key);

		for (int i = 0; i < size; i++) {
			dataList.add((T) listOperation.index(key,i));
		}
		return dataList;
	}

	/**
	 * 缓存Map
	 *
	 * @param key 缓存的键值
	 * @param dataMap
	 * @return 缓存键值对应的数据
	 */
	@Override
	public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {

		HashOperations hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<String, T> entry : dataMap.entrySet()) {
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	@Override
	public <T> Map<Object, Object> getCacheMap(String key) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		return map;
	}
}
