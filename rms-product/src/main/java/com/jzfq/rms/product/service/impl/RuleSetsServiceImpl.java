package com.jzfq.rms.product.service.impl;

import com.jzfq.rms.product.bean.DecisionSets;
import com.jzfq.rms.product.bean.RuleSets;
import com.jzfq.rms.product.bean.RuleSetsExample;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.dao.IRuleSetsDAO;
import com.jzfq.rms.product.service.IRuleSetsService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class RuleSetsServiceImpl implements IRuleSetsService {
    @Autowired
    private IRuleSetsDAO ruleSetsDAO;

    private static final String TACTICS_NO = "tactics_No";
    /**
     * 根据主键值获取实体
     * @param check_item_id 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public RuleSets getByPK(Integer check_item_id) throws BusinessException {
        RuleSets dic = ruleSetsDAO.selectByPrimaryKey(check_item_id);
        return dic;
    }

    /**
     * 根据条件获取列表
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<RuleSets> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException{
        RuleSetsExample example = new RuleSetsExample();
        RuleSetsExample.Criteria criteria= example.createCriteria();
        if(!StringUtil.isEmpty(conditionMap.get(TACTICS_NO))) {
            criteria.andTacticsNoEqualTo(String.valueOf(conditionMap.get(TACTICS_NO)));
        }
        example.setOrderByClause("rule_sets_serial ASC");
        List<RuleSets> dicList= ruleSetsDAO.selectByExampleWithBLOBs(example);
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
    public int insert(RuleSets record) throws BusinessException{
        record.setCreateTime(DateUtils.now());
        int index = ruleSetsDAO.insertSelective(record);
        //加入redis缓存
//        cacheWithRedis.addDictionary(record);
        return index;
    }

    /**
     * 更新新的model中不为空的字段。
     * @param record 实体
     * @return 更新条数
     * @throws BusinessException 业务异常
     */
    @Override
    public int updateByPrimaryKeySelective(RuleSets record)throws BusinessException {
        return ruleSetsDAO.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据策略编号获取规则集信息
     * @param tacticsNo
     * @return
     * @throws BusinessException
     */
    @Override
    public List<RuleSets> getRuleList(String tacticsNo) throws BusinessException {
        RuleSetsExample example = new RuleSetsExample();
        RuleSetsExample.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmpty(tacticsNo)) {
            criteria.andTacticsNoEqualTo(tacticsNo);
        }
        return ruleSetsDAO.selectByExample(example);
    }
}