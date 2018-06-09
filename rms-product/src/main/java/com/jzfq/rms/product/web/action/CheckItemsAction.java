package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.CheckItems;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.ICheckItemsService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestController
@RequestMapping(value = "/inner")
public class CheckItemsAction {

    @Autowired
    private ICheckItemsService service;

    @RequestMapping(value = "/checkItems/read",method = RequestMethod.POST)
    public ResponseResult getRecord(String systemID,String traceID, String params) throws Exception {
        LoggerUtils.trace(CheckItemsAction.class,traceID,"001","getRecord start",systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {});
        List<CheckItems> resultList = service.getAllByCondition(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

    @RequestMapping(value = "/checkItems/save",method = RequestMethod.POST)
    public ResponseResult saveRecord(String systemID,String traceID, String params) throws Exception {
        LoggerUtils.trace(CheckItemsAction.class,traceID,"001","getRecord start",systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        CheckItems record = JSON.parseObject(params, new TypeReference<CheckItems>() {});
        int result = service.insert(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
    }
}