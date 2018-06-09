package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.IMutilpleService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.jzfq.rms.product.constant.ResponseCode.BUSINESS_PARAM_ERROR;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestController
@RequestMapping(value = "/inner")
public class MultipleAction {

    @Autowired
    private IMutilpleService mutilpleService;

    private static final String TACTICS_ID = "tactics_id";

    /**
     * 生成策略IDkey
     * @param traceID 链路ID
     * @param params  请求参数
     * @return 返回结果
     * @throws BusinessException 异常信息
     */
    @RequestMapping(value = "/multiple/addTacticsKey")
    public ResponseResult addTacticsKey(String traceID, String params) throws Exception {
        try {
            int result = mutilpleService.addTacticsKey();
            return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
        } catch (BusinessException e) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务没开通", true);
        }
    }

    /**
     * 生成策略
     * @param traceID 链路ID
     * @param params  请求参数
     * @return 返回结果
     * @throws BusinessException 异常信息
     */
    @RequestMapping(value = "/multiple/addTactics")
    public ResponseResult addTactics(String traceID, String params) throws Exception {
        try {
            TacticsRecord tacticsRecord = new TacticsRecord();
            Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {});
            tacticsRecord.setTacticsNo(String.valueOf(paramsMap.get("tactics_No")));
            int result = mutilpleService.addTactics(tacticsRecord);
            return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
        }catch (BusinessException e) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务没开通", true);
        }
    }

    /**
     * 根据TacticsNo获取多个产品内部版本号的风控策略基本信息
     * @param systemID 调用系统ID
     * @param traceID  链路ID
     * @param params   请求参数
     * @return 返回结果
     * @throws BusinessException 异常信息
     */
    @RequestMapping(value = "/multiple/readByTacticsNo")
    public ResponseResult getRecordByTacticsNo(String systemID, String traceID, String params) throws Exception {
        LoggerUtils.trace(MultipleAction.class, traceID, "001", "getRecordByTacticsNo start", systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });
        final String TACTICS_NO = "tactics_No";
        if (StringUtil.isEmpty(paramsMap.get(TACTICS_NO))) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "tactics_No不能为空", true);
        }
        List<TacticsRecord> resultList = mutilpleService.getRecordByTacticsNo(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

    /**
     * 根据订单策略编号获取风控策略基本信息
     * @param traceID 链路ID
     * @param params  请求参数
     * @return 返回结果
     * @throws BusinessException 异常信息
     */
    @RequestMapping(value = "/multiple/readByProductOrderNo")
    public ResponseResult getRecordByProductOrderNo(String systemID, String traceID, String params) throws Exception {
        LoggerUtils.trace(MultipleAction.class, traceID, "001", "readByProductOrderNo start", systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(params)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });
        final String PRODUCT_ORDER_NO = "product_order_No";
        if (StringUtil.isEmpty(paramsMap.get(PRODUCT_ORDER_NO))) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "productOrderNo不能为空", true);
        }
        List<ProductInfo> resultList = mutilpleService.getRecordByProductOrderNo(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

    /**
     * 根据策略ID,内外规则分类获取策略基本信息集合
     * @param systemID 调用系统ID
     * @param traceID  链路ID
     * @param paramsMap 请求参数
     * @return 返回结果
     * @throws BusinessException 异常信息
     */
    @RequestMapping(value = "/multiple/readTacticsConfigByTacticsID")
    public ResponseResult getTacticsConfigByTacticsID(String systemID, String traceID, @RequestBody Map<String, Object> paramsMap) throws Exception {
        LoggerUtils.trace(MultipleAction.class, traceID, "001", "readTacticsConfigByTacticsID start", systemID);
        if (MapUtils.isEmpty(paramsMap)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        if (StringUtil.isEmpty(paramsMap.get(TACTICS_ID))) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "tactics_id不能为空", true);
        }
        String tacticsID = String.valueOf(paramsMap.get(TACTICS_ID));
        TacticsRecord tacticsRecord = mutilpleService.getActiveProductTacticsByTacticsID(tacticsID);
        tacticsRecord.setRuleSets(JSON.parse(tacticsRecord.getRuleSets().toString()));
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", tacticsRecord);

    }

    /**
     * 根据主键值策略id,内外规则分类获取产品策略信息集合的规则集和决策集信息`
     * @param traceID 链路ID
     * @param params  请求参数
     * @return 返回结果
     * @throws BusinessException 异常信息
     */
    @RequestMapping(value = "/multiple/readRulesAndDecisionByTacticsID")
    public ResponseResult getRulesAndDecisionByTacticsID(String systemID, String traceID, String params) throws Exception {
        LoggerUtils.trace(MultipleAction.class, traceID, "001", "readByProductOrderNo start", systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });
        final String RULES_OI_TYPE = "rules_oi_type";
        if (StringUtil.isEmpty(paramsMap.get(TACTICS_ID)) && !StringUtil.isEmpty(paramsMap.get(RULES_OI_TYPE))) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "tactics_id和rules_oi_type不能为空", true);
        }
        Integer tacticsID = Integer.valueOf(String.valueOf(paramsMap.get(TACTICS_ID)));
        Integer rules_oi_type = Integer.valueOf(String.valueOf(paramsMap.get(RULES_OI_TYPE)));
        List<Map> resultList = mutilpleService.getRulesAndDecisionByTacticsID(tacticsID, rules_oi_type);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

}