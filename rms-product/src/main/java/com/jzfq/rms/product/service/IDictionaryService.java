package com.jzfq.rms.product.service;


import com.jzfq.rms.product.bean.Dictionary;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public interface IDictionaryService {

    Dictionary getByPK(Integer dictionaryID) throws BusinessException;

    List<Dictionary> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException;

    List<String> getvalueByCondition(Map<String, Object> conditionMap) throws BusinessException;

    long getCountByCondition(Map conditionMap) throws BusinessException;

    int insert(Dictionary record) throws BusinessException;

    List<Map<String, Object>> getDictionary(String type) throws BusinessException;

    String getValue(String key,String type)throws BusinessException;

    /**
     * 分页查询字典信息
     * @param type
     * @param dictionaryName
     * @return
     */
    PageData<Dictionary> queryList(String type, String dictionaryName);
}