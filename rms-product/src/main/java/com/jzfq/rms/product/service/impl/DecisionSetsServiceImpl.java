package com.jzfq.rms.product.service.impl;

import com.jzfq.rms.product.bean.DecisionSets;
import com.jzfq.rms.product.bean.DecisionSetsExample;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.dao.IDecisionSetsDAO;
import com.jzfq.rms.product.service.IDecisionSetsService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class DecisionSetsServiceImpl implements IDecisionSetsService {
    @Autowired
    private IDecisionSetsDAO dao;
//    @Autowired
//    private DictionaryCacheWithRedis cacheWithRedis;
    /**
     * 根据主键值获取实体
     * @param check_item_id 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public DecisionSets getByPK(Integer check_item_id) throws BusinessException {
        DecisionSets dic = dao.selectByPrimaryKey(check_item_id);
        return dic;
    }

    /**
     * 根据条件获取列表
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<DecisionSets> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException{
        DecisionSetsExample example = new DecisionSetsExample();
        DecisionSetsExample.Criteria criteria= example.createCriteria();
        if(!StringUtil.isEmpty(conditionMap.get("rule_sets_No"))) {
            criteria.andRuleSetsNoEqualTo(String.valueOf(conditionMap.get("rule_sets_No")));
        }
        List<DecisionSets> dicList= dao.selectByExample(example);
        return dicList;
    }

    /**
     * 根据条件获取总条数
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 总条数
     * @throws BusinessException 业务异常
     */
    @Override
    public long getCountByCondition(Map conditionMap) throws BusinessException{
        int size =0;
        List<DecisionSets> dicList= getAllByCondition(conditionMap);
        if(dicList!=null && dicList.size()>0){
            size = dicList.size();
        }
        return size;
    }

    /**
     * 增加实体
     * @param record 实体
     * @return 插入条数
     * @throws BusinessException 业务异常
     */
    @Override
    public int insert(DecisionSets record) throws BusinessException{
        record.setCreateTime(DateUtils.now());
        int index = dao.insertSelective(record);
        //加入redis缓存
//        cacheWithRedis.addDictionary(record);
        return index;
    }

}