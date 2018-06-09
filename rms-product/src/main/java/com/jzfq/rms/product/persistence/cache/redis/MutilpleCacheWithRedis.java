package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.IMutilpleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public class MutilpleCacheWithRedis extends CacheWithRedis implements IMutilpleCache {

	private final Logger LOG = LoggerFactory.getLogger(MutilpleCacheWithRedis.class);

	private static final String RISK_TACTICS_PREFIX = "risk_tactics_prefix";
	private static final String RISK_PRODUCT_PREFIX = "risk_product_prefix";

	@Override
	public <T> List<T> getProductCacheListByCondition(Map conditionMap) throws BusinessException {
		String key = RISK_PRODUCT_PREFIX+"_"+conditionMap.get("product_orderNo");
		List<T> value = getCacheList(key);
		return value;

	}

	@Override
	public <T>void setProductCacheList(Integer product_orderNo,List<T> recordList) {

		String key = RISK_PRODUCT_PREFIX+"_"+product_orderNo;
		setCacheList(key,recordList);
	}

	@Override
	public TacticsRecord getActiveProductTacticsByTacticsID(Integer tacticsID) throws BusinessException {
		String key = RISK_TACTICS_PREFIX+"_"+tacticsID;
		TacticsRecord value = JSON.parseObject(getCacheString(key), new TypeReference<TacticsRecord>() {});
		return value;

	}

	@Override
	public void setActiveProductTacticsCache(TacticsRecord record) {

		String key = RISK_TACTICS_PREFIX+"_"+record.getTacticsId();
		String value = JSON.toJSONString(record);
		setCacheString(key,value);
	}

	@Override
	public <T> List<T> getRulesAndDecisionCacheListByCondition(Map conditionMap) throws BusinessException {
		String key = RISK_TACTICS_PREFIX+"_"+conditionMap.get("tacticsID")
				+"_"+conditionMap.get("tactics_configID")
				+"_"+conditionMap.get("rules_oi_type")
				+"_"+conditionMap.get("setID");
		List<T> value = JSON.parseObject(getCacheString(key),new TypeReference<List<T>>() {});
		return value;

	}

	@Override
	public <T>void setRulesAndDecisionCacheList(Integer tacticsID, Integer tactics_configID, Integer setID, Integer rules_oi_type, List<T> recordList) {

		String key = RISK_TACTICS_PREFIX+"_"+tacticsID+"_"+tactics_configID+"_"+rules_oi_type+"_"+setID;
		String value = JSON.toJSONString(recordList);
		setCacheString(key,value);
	}

	@Override
	public <T> void setTacticsConfigCacheList(Integer tacticsID, Integer rules_oi_type, List<T> recordList) {

		String key = RISK_TACTICS_PREFIX+"_"+tacticsID+"_"+rules_oi_type;
		String value = JSON.toJSONString(recordList);
		setCacheString(key,value);
	}

	@Override
	public <T> List<T> getTacticsConfigCacheListByCondition(Map conditionMap) throws BusinessException {
		return null;
	}


}
