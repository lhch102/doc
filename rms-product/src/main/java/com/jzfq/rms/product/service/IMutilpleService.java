package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 21:04:55
 */
public interface IMutilpleService {

    int addTacticsKey() throws BusinessException;

    int addTactics(TacticsRecord tacticsRecord) throws BusinessException;

    List<TacticsRecord> getRecordByTacticsNo(Map<String, Object> conditionMap) throws BusinessException;

    List<ProductInfo> getRecordByProductOrderNo(Map<String, Object> conditionMap) throws BusinessException;

    TacticsRecord getActiveProductTacticsByTacticsID(String tacticsID) throws BusinessException;

    List<Map> getRulesAndDecisionByTacticsID(Integer tacticsID, Integer rules_oi_type) throws BusinessException;
}
