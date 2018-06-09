package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.bean.RuleClassification;
import com.jzfq.rms.product.bean.RuleInfo;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.IRuleInfoService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则处理类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestController
@RequestMapping(value = "/inner")
public class RuleInfoAction {

    @Autowired
    private IRuleInfoService service;

    /**
     * 获取执行规则分类接口
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param params   请求参数
     * @return 返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/ruleInfo/read", method = RequestMethod.POST)
    public ResponseResult getRecord(String systemID, String traceID, String params) throws Exception {
        LoggerUtils.trace(RuleInfoAction.class, traceID, "001", "getRecord start", systemID);
        if (StringUtils.isNotBlank(params)) {
            Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
            });

            if (StringUtils.isNotBlank(traceID)) {
                List<RuleInfo> resultList = service.getAllByCondition(paramsMap);
                return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
            } else {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "traceID不能为空", true);
            }
        } else {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
    }

    /**
     * 保存规则信息
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param record   请求参数
     * @return 返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/ruleInfo/save", method = RequestMethod.POST)
    public ResponseResult saveRecord(String systemID, String traceID, @RequestBody RuleInfo record) throws Exception {
        LoggerUtils.trace(RuleInfoAction.class, traceID, "001", "saveRecord start", systemID);
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        if (record == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }

        int result = service.insert(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
    }

    /**
     * 获取执行规则层级关系；
     * 前端选择下拉框时，每次访问后台查询二级规则
     *
     * @param systemID  调用系统ID
     * @param traceID   链路跟踪ID
     * @param paramsMap 请求参数
     * @return 返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/ruleInfo/getRuleClassification", method = RequestMethod.POST)
    public ResponseResult getRuleClassification(String systemID, String traceID, @RequestBody Map<String, Object> paramsMap) throws Exception {
        LoggerUtils.trace(RuleInfoAction.class, traceID, "001", "getRecord start", systemID);
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        if (MapUtils.isEmpty(paramsMap)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }

        //规则分类/规则层级
        final String RULE_CLASSIFICATION = "ruleClassification";
        //策略类型
        final String TACTICS_TYPE = "tacticsType";
        if (StringUtil.isEmpty(paramsMap.get(RULE_CLASSIFICATION)) || StringUtil.isEmpty(paramsMap.get(TACTICS_TYPE))) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "规则层级（ruleClassification）和策略类型（tacticsType）不能为空", true);
        }
        List<Map<String, Object>> ruleClassification = service.getRuleClassification(paramsMap);
        Map<String,List> map = new HashMap<>(2);
        map.put("list",ruleClassification);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", map);
    }

    /**
     * 获取执行规则层级关系；打包成json返回到前端
     * 前端给一个tacticsType（策略类型）参数，查询该类型下所有的属性，一次打包返回到前端
     *
     * @param systemID  调用系统ID
     * @param traceID   链路跟踪ID
     * @param paramsMap 请求参数
     * @return 返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/ruleInfo/getRuleClassificationBale", method = RequestMethod.POST)
    public ResponseResult getRuleClassificationBale(String systemID, String traceID, @RequestBody Map<String, Object> paramsMap) throws Exception {
        LoggerUtils.trace(RuleInfoAction.class, traceID, "001", "getRecord start", systemID);
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        if (MapUtils.isEmpty(paramsMap)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }

        //策略类型
        final String TACTICS_TYPE = "tacticsType";
        if (StringUtil.isEmpty(paramsMap.get(TACTICS_TYPE))) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "tacticsType不能为空", true);
        }
        List<RuleClassification> ruleName = service.getRuleClassificationBale(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", ruleName);
    }
}