package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.CheckItems;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public interface ICheckItemsService {
    CheckItems getByPK(Integer check_item_id) throws BusinessException;

    List<CheckItems> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException;

    long getCountByCondition(Map conditionMap) throws BusinessException;

    int insert(CheckItems record) throws BusinessException;
}
