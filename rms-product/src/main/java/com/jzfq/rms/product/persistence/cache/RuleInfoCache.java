package com.jzfq.rms.product.persistence.cache;

import com.jzfq.rms.product.bean.RuleClassification;
import com.jzfq.rms.product.bean.RuleInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/1/31 9:29
 */
public interface RuleInfoCache {

    /**
     * 将数据json加入缓存
     * @param mapKey
     * @param value
     */
    void addRuleInfoByType(Map<String,Object> mapKey,String value);

    /**
     * 根据tacticsType查询规则
     * @param tacticsType
     * @return
     */
    List<RuleClassification> getRuleInfoByType(Integer tacticsType);

    List<Map<String,Object>> getRuleInfoByCondition(Map conditionMap);
}
