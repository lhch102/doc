package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.RuleSets;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public interface IRuleSetsService {
    RuleSets getByPK(Integer check_item_id) throws BusinessException;

    List<RuleSets> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException;

    long getCountByCondition(Map conditionMap) throws BusinessException;

    int insert(RuleSets record) throws BusinessException;

    int updateByPrimaryKeySelective(RuleSets record)throws BusinessException;

    /**
     * 根据策略编号获取规则集信息
     * @param tacticsNo
     * @return
     * @throws BusinessException
     */
    List<RuleSets> getRuleList(String tacticsNo) throws BusinessException;
}
