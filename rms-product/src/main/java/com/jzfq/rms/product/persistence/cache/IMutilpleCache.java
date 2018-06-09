package com.jzfq.rms.product.persistence.cache;


import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public interface IMutilpleCache {


    <T> List<T> getProductCacheListByCondition(Map conditionMap) throws BusinessException;

    <T>void setProductCacheList(Integer product_orderNo, List<T> recordList);

    TacticsRecord getActiveProductTacticsByTacticsID(Integer tacticsID) throws BusinessException;

    void setActiveProductTacticsCache(TacticsRecord record);

    <T> List<T> getRulesAndDecisionCacheListByCondition(Map conditionMap) throws BusinessException;

    <T>void setRulesAndDecisionCacheList(Integer tacticsID, Integer tactics_configID, Integer setID, Integer rules_oi_type, List<T> recordList);

    <T> void setTacticsConfigCacheList(Integer tacticsID, Integer rules_oi_type, List<T> recordList);

    <T> List<T> getTacticsConfigCacheListByCondition(Map conditionMap) throws BusinessException;
}
