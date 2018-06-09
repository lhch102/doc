package com.jzfq.rms.product.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.*;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 综合查询
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class MutilpleServiceImpl implements IMutilpleService {
    /**
     * 策略
     */
    @Autowired
    private ITacticsRecordService tacticsRecordService;
    /**
     * 规则集
     */
    @Autowired
    private IRuleSetsService ruleSetsService;
    /**
     * 规则参数集
     */
    @Autowired
    private IRuleParamSetsService ruleParamSetsService;
    /**
     * 产品集
     */
    @Autowired
    private IProductInfoService productInfoService;

    @Override
    public int addTacticsKey() throws BusinessException{
        // TODO 策略编号生成规则待确认
        return 0;
    }
    @Override
    public int addTactics(TacticsRecord tacticsRecord) throws BusinessException{
        Map<String, Object> tacticsConditionMap = new HashMap();
//        tacticsConditionMap.put("tactics_No",tacticsRecord.getTacticsNo());
            List<TacticsRecord> tacticsRecordList= tacticsRecordService.getAllByCondition(tacticsConditionMap);
            for(TacticsRecord tactics:tacticsRecordList) {
                Map<String, Object> conditionMap = new HashMap();
                conditionMap.put("tactics_No",tactics.getTacticsNo());
                List<RuleSets> ruleSetsList = ruleSetsService.getAllByCondition(conditionMap);
                List<RuleSetsDisplay> ruleSetsValueList = new ArrayList<>();

                for(RuleSets ruleSets:ruleSetsList) {
                    RuleSetsDisplay ruleSetsDisplay = new RuleSetsDisplay();
                    Map<String, Object> ruleSetsConditionMap = new HashMap();
                    ruleSetsConditionMap.put("rule_sets_No",ruleSets.getRuleSetsNo());
                    List<RuleParamSets> ruleParamSetsList = ruleParamSetsService.getAllByCondition(ruleSetsConditionMap);

                    ruleSetsDisplay.setTacticsNo(ruleSets.getTacticsNo());
                    Map<String, Object> paramsMap = JSON.parseObject(ruleSets.getDecisionSets(), new TypeReference<Map<String, Object>>() {});
                    ruleSetsDisplay.setDecisionSets(paramsMap);
                    ruleSetsDisplay.setExecuteKey(ruleSets.getExecuteKey());
                    ruleSetsDisplay.setExecuteKeyContent(ruleSets.getExecuteKeyContent());
                    ruleSetsDisplay.setHitNo(ruleSets.getHitNo());
                    ruleSetsDisplay.setMailSets(ruleSets.getMailSets());
                    ruleSetsDisplay.setOiType(ruleSets.getOiType());
                    ruleSetsDisplay.setOiTypeName(ruleSets.getOiTypeName());

                    ruleSetsDisplay.setRuleParamSets(ruleParamSetsList);

                    ruleSetsDisplay.setRuleSetsId(ruleSets.getRuleSetsId());
                    ruleSetsDisplay.setRuleSetsName(ruleSets.getRuleSetsName());
                    ruleSetsDisplay.setRuleSetsNo(ruleSets.getRuleSetsNo());
                    ruleSetsDisplay.setRuleSetsSerial(ruleSets.getRuleSetsSerial());
                    ruleSetsDisplay.setRuleSetsState(Integer.valueOf(ruleSets.getRuleSetsState()));
                    ruleSetsDisplay.setTacticsNo(ruleSets.getTacticsNo());
                    ruleSetsDisplay.setTacticsId(ruleSets.getTacticsId());
                    ruleSetsDisplay.setWeightScore(ruleSets.getWeightScore());
                    ruleSetsValueList.add(ruleSetsDisplay);
                    //ruleSetsService.updateByPrimaryKeySelective(ruleSets);
                }
                tactics.setRuleSets(JSON.toJSONString(ruleSetsValueList));
                tacticsRecordService.updateByPrimaryKeySelective(tactics);
            }

        return 0;
    }
    @Override
    public List<TacticsRecord> getRecordByTacticsNo(Map<String, Object> conditionMap) throws BusinessException {

        List<TacticsRecord> tacticsRecordList = tacticsRecordService.getAllByCondition(conditionMap);
        for(TacticsRecord tactics:tacticsRecordList) {
            JSONArray jsonArray = getRuleSetsList(tactics);
            for(int i=0;i<jsonArray.size();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map decisionSetsMap = getDecisionSetsMap(jsonObject);
                List<RuleParamSets> RuleParamSetsList = getRuleParamSetsList(jsonObject);
//                JSONObject decisionSetsJsonObject = jsonObject.getJSONObject("decisionSets");//决策集合
//                JSONArray jsonArrayRuleParamSets = jsonObject.getJSONArray("ruleParamSets");//规则集合
//                String executeKeyContent = jsonObject.getString("executeKeyContent");//基本信息
            }
        }
        return tacticsRecordList;
    }

    /**
     * 获取决策集合信息
     * @param jsonObject
     * @return
     * @throws BusinessException
     */
    private Map getDecisionSetsMap(JSONObject jsonObject) throws BusinessException {
        //决策集合
        JSONObject decisionSetsJsonObject = jsonObject.getJSONObject("decisionSets");
        Map resultMap = JSON.parseObject(decisionSetsJsonObject.toString(), new TypeReference<HashMap<String,String>>() {});
        return resultMap;
    }

    /**
     * 获取规则集合信息
     * @param tactics
     * @return
     * @throws BusinessException
     */
    private JSONArray getRuleSetsList(TacticsRecord tactics) throws BusinessException {
        JSONArray jsonArrayRuleSets = JSONArray.parseArray(tactics.getRuleSets().toString());
        return jsonArrayRuleSets;
    }

    /**
     * 获取规则参数信息
     * @param jsonObject
     * @return
     * @throws BusinessException
     */
    private List<RuleParamSets> getRuleParamSetsList(JSONObject jsonObject) throws BusinessException {
        //规则集合
        JSONArray jsonArrayRuleParamSets = jsonObject.getJSONArray("ruleParamSets");
        List<RuleParamSets> collection = JSONObject.parseArray(jsonArrayRuleParamSets.toString(),RuleParamSets.class);
        return collection;
    }

    @Override
    public List<ProductInfo> getRecordByProductOrderNo(Map<String, Object> conditionMap) throws BusinessException {
        List<ProductInfo> productInfoList = productInfoService.getAllByCondition(conditionMap);
        return productInfoList;
    }

    @Override
    public TacticsRecord getActiveProductTacticsByTacticsID(String tacticsID) throws BusinessException {
        TacticsRecord tacticsRecord = tacticsRecordService.getByPK(tacticsID);
        return tacticsRecord;
    }

    @Override
    public List<Map> getRulesAndDecisionByTacticsID(Integer tacticsID, Integer rules_oi_type) throws BusinessException {
        return null;
    }
}