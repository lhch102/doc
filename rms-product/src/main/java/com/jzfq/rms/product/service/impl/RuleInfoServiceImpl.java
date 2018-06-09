package com.jzfq.rms.product.service.impl;

import com.jzfq.rms.product.bean.RuleClassification;
import com.jzfq.rms.product.bean.RuleInfo;
import com.jzfq.rms.product.bean.RuleInfoExample;
import com.jzfq.rms.product.constant.SystemConstants;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.RuleInfoCache;
import com.jzfq.rms.product.persistence.dao.IRuleInfoDAO;
import com.jzfq.rms.product.service.IRuleInfoService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.JsonUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class RuleInfoServiceImpl implements IRuleInfoService {

    @Autowired
    private IRuleInfoDAO ruleInfoDAO;

    private static final Logger log = LoggerFactory.getLogger(RuleInfoServiceImpl.class);

    private static final String RULE_INFO_PREFIX = "rule_info_prefix";

    @Autowired
    private RuleInfoCache cacheWithRedis;

    /**
     * 规则层级
     */
    private static final String RULE_CLASSIFICATION = "ruleClassification";
    /**
     * 策略类型
     */
    private static final String TACTICS_TYPE = "tacticsType";
    private static final String PARENT_NO = "parentNo";

    /**
     * 根据主键值获取实体
     *
     * @param id 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public RuleInfo getByPK(Integer id) throws BusinessException {
        RuleInfo dic = ruleInfoDAO.selectByPrimaryKey(id);
        return dic;
    }

    /**
     * 根据条件获取列表
     *
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<RuleInfo> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException {
        RuleInfoExample example = new RuleInfoExample();
        RuleInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmpty(conditionMap.get(RULE_CLASSIFICATION))) {
            criteria.andRuleClassificationEqualTo((Integer) conditionMap.get(RULE_CLASSIFICATION));
        }
        final String RULE_NO = "rule_No";
        if (!StringUtil.isEmpty(conditionMap.get(RULE_NO))) {
            criteria.andRuleNoEqualTo(String.valueOf(conditionMap.get(RULE_NO)));
        }
        criteria.andDeleteflagEqualTo((int) SystemConstants.DELETE_FLAG_0);
        List<RuleInfo> dicList = ruleInfoDAO.selectByExample(example);
        return dicList;
    }

    /**
     * 根据条件获取总条数
     *
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 总条数
     * @throws BusinessException 业务异常
     */
    @Override
    public long getCountByCondition(Map conditionMap) throws BusinessException {
        int size = 0;
        List<RuleInfo> dicList = getAllByCondition(conditionMap);
        if (dicList != null && dicList.size() > 0) {
            size = dicList.size();
        }
        return size;
    }

    /**
     * 增加实体
     *
     * @param record 实体
     * @return 插入条数
     * @throws BusinessException 业务异常
     */
    @Override
    public int insert(RuleInfo record) throws BusinessException {
        record.setCreateTime(DateUtils.now());
        record.setUpdateTime(DateUtils.now());
        record.setDeleteflag((int) SystemConstants.DELETE_FLAG_0);
        int index = ruleInfoDAO.insertSelective(record);
        //加入redis缓存
        // cacheWithRedis.addDictionary(record);
        return index;
    }

    /**
     * 获取执行规则层级关系；
     * 前端选择下拉框时，每次访问后台查询二级规则
     * @param conditionMap
     * @return
     * @throws BusinessException
     */
    @Override
    public List<Map<String,Object>> getRuleClassification(Map<String, Object> conditionMap) throws BusinessException {
        StringBuilder key = new StringBuilder("_");
        //规则层级
        Integer ruleClassification = Integer.valueOf(conditionMap.get(RULE_CLASSIFICATION).toString().trim());
        key.append(ruleClassification);
        //策略类型
        Integer tacticsType = (Integer.valueOf(conditionMap.get(TACTICS_TYPE).toString().trim()));
        key.append("_").append(tacticsType.toString());
        Object parentNo = conditionMap.get(PARENT_NO);
        if (!StringUtil.isEmpty(parentNo)) {
            key.append("_").append(parentNo);
        }
        Map<String,Object> mapKey = new HashMap<>(2);
        mapKey.put("key",key);
        List<Map<String,Object>> dicList = cacheWithRedis.getRuleInfoByCondition(mapKey);
        if (dicList == null || dicList.size() == 0){
            RuleInfoExample example = new RuleInfoExample();
            RuleInfoExample.Criteria criteria = example.createCriteria();
            criteria.andRuleClassificationEqualTo(ruleClassification);
            criteria.andTacticsTypeEqualTo(tacticsType);
            if (!StringUtil.isEmpty(parentNo)) {
                criteria.andParentNoEqualTo(parentNo.toString());
            }
            criteria.andDeleteflagEqualTo((int) SystemConstants.DELETE_FLAG_0);
            dicList = ruleInfoDAO.getRuleClassiName(example);
            cacheWithRedis.addRuleInfoByType(mapKey,JsonUtils.toJSON(dicList));
        }
        return dicList;
    }

    @Override
    public List<RuleClassification> getRuleClassificationBale(Map<String, Object> paramsMap) {
        Integer tacticsType = (Integer)paramsMap.get(TACTICS_TYPE);
        List<RuleClassification> resultList = cacheWithRedis.getRuleInfoByType(tacticsType);
        if (resultList == null || resultList.size() == 0){
            RuleInfoExample example = new RuleInfoExample();
            RuleInfoExample.Criteria criteria = example.createCriteria();
            criteria.andTacticsTypeEqualTo(tacticsType);

            //一级分类
            criteria.andRuleClassificationEqualTo(1);
            criteria.andDeleteflagEqualTo((int) SystemConstants.DELETE_FLAG_0);
            //查询一级规则
            List<RuleClassification> ruleClassification = ruleInfoDAO.getRuleClassificationFirst(example);

            Map<String, Object> mapParam = new HashMap<>(ruleClassification.size() + 2, 1f);
            //二级分类
            mapParam.put("ruleClassification", 2);
            mapParam.put("deleteFlag", 0);
            resultList = new ArrayList<>();
            for (RuleClassification classification : ruleClassification) {
                mapParam.put("parentNo", classification.getRuleNo());
                //根据parentNo查询二级规则
                List<RuleClassification> ruleClassification2 = ruleInfoDAO.getRuleClassificationSecond(mapParam);
                classification.setRuleClassiclist(ruleClassification2);
                resultList.add(classification);
            }
            Map<String,Object> mapKey = new HashMap<>(2);
            mapKey.put("key",tacticsType);
            cacheWithRedis.addRuleInfoByType(mapKey, JsonUtils.toJSON(resultList));
        }
        return resultList;
    }

}