package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.RuleClassification;
import com.jzfq.rms.product.bean.RuleInfo;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 22:23:15
 */
public interface IRuleInfoService {

    RuleInfo getByPK(Integer id) throws BusinessException;

    List<RuleInfo> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException;

    long getCountByCondition(Map conditionMap) throws BusinessException;

    int insert(RuleInfo record) throws BusinessException;

    List<Map<String,Object>> getRuleClassification(Map<String, Object> conditionMap) throws BusinessException;

    List<RuleClassification> getRuleClassificationBale(Map<String, Object> paramsMap);
}
