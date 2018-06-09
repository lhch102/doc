package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.Dictionary;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.IDictionaryService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestController
@RequestMapping(value = "/inner")
public class DictionaryAction {

    private final static Logger logger = LoggerFactory.getLogger(CustomerTypeAction.class);

    @Autowired
    private IDictionaryService service;

    /**
     * 获取字典项
     * @param systemID  调用系统ID
     * @param traceID   链路跟踪ID
     * @param params    请求参数
     * @return          返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/dictionary/read",method = RequestMethod.POST)
    public ResponseResult getRecord(String systemID,String traceID, String params) throws Exception {
        LoggerUtils.trace(DictionaryAction.class,traceID,"001","getRecord start",systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/

        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });
        List<Dictionary> resultList = service.getAllByCondition(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

    @RequestMapping(value = "/dictionary/save",method = RequestMethod.POST)
    public ResponseResult saveRecord(String systemID,String traceID, String params) throws Exception {
        LoggerUtils.trace(DictionaryAction.class,traceID,"001","getRecord start",systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        Dictionary record = JSON.parseObject(params, new TypeReference<Dictionary>() {
        });
        int result = service.insert(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
    }

    @RequestMapping(value = "/dictionary/addAllDictionary")
    public ResponseResult addAllDictionary(String traceID, String params) throws Exception {
        Map<String, Object> conditionMap = new HashMap();
        List<String> lst = service.getvalueByCondition(conditionMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", lst);

    }

    /**
     * 获取字典项
     * @param params    请求参数
     * @return          返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/dictionary/getDictionary",method = RequestMethod.POST)
    public ResponseResult getDictionary(@RequestBody JSONObject params) throws Exception {
        logger.info("入参：",params);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        String traceID = params.getString("traceID");
        String systemID = params.getString("systemID");
        LoggerUtils.trace(DictionaryAction.class,traceID,"001","getRecord start",systemID);

        String type = params.getString("type");
        if (StringUtil.isEmpty(type)){
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "字典type不能为空", true);
        }
        List<Map<String, Object>> resultList = service.getDictionary(type);
        Map<String,List> map = new HashMap(2);
        map.put("list",resultList);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", map);
    }

    @RequestMapping(value = "/dictionary/getDictionaryPage",method = RequestMethod.POST)
    public ResponseResult getDictionaryPage(String traceID,@RequestBody JSONObject params) throws Exception {
        logger.info("入参：", params);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        String type = params.getString("type");
        if(StringUtil.isEmpty(type)){
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "参数：type不能为空", true);
        }
        String dictionaryName = params.getString("dictionaryName");
        PageData<Dictionary> dictionaryPageData = service.queryList(type,dictionaryName);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", dictionaryPageData);

    }

    }