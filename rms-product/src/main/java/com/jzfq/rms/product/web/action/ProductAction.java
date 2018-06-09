package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.IProductInfoService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * 产品订单控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestController
@RequestMapping(value = "/inner")
public class ProductAction {

    @Autowired
    private IProductInfoService service;

    /**
     * 根据条件获取产品信息
     *
     * @param traceID
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/read", method = RequestMethod.POST)
    public ResponseResult getRecord(String traceID, String params) throws Exception {
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });
        List<ProductInfo> resultList = service.getAllByCondition(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

    /**
     * 获取所有产品信息
     *
     * @param traceID
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/readAll")
    public ResponseResult getAllRecord(String traceID, String params) {

        try {
            if (StringUtils.isNotBlank(params)) {
                if (StringUtils.isNotBlank(traceID)) {
                    List<ProductInfo> resultList = service.getAll();
                    return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
                } else {
                    throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "traceID不能为空", true);
                }
            } else {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据产品id获取单个产品信息
     *
     * @param traceID
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/readByProductId", method = RequestMethod.POST)
    public ResponseResult getRecordByProductId(String traceID, @RequestBody JSONObject params) throws Exception {
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        Integer productId = params.getInteger("productId");
        if (productId == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "productId不能为空", true);
        }
        ProductInfo result = service.getByPK(productId);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
    }

    /**
     * 保存产品信息
     *
     * @param traceID
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public ResponseResult saveRecord(String traceID, @RequestBody @Valid ProductInfo record, BindingResult bindingResult) throws Exception {
        String sb = hasErrors(bindingResult);
        if (sb != null) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb, true);
        }
        if (record == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        boolean b = service.checkProductName(record.getProductId(), record.getProductName());
        if (!b) {
            throw new BusinessException(ResponseCode.REPEAT, "订单策略名称不能重复", true);
        }
        int result = service.insert(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "保存成功", result);
    }

    /**
     * 订单策略分页数据
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param record   请求参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/getProductTacticsPage", method = RequestMethod.POST)
    public ResponseResult getProductTacticsPage(String systemID, String traceID, @RequestBody ProductInfo record) throws Exception {
        LoggerUtils.trace(ProductAction.class, traceID, "001", "getProductTacticsPage start", systemID);
        if (record == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        PageData<ProductInfo> productInfoPageData = service.queryList(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", productInfoPageData);
    }

    /**
     * 逻辑删除订单策略
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param params   请求参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/delProductTactics", method = RequestMethod.POST)
    public ResponseResult delProductTactics(String systemID, String traceID, @RequestBody JSONObject params) throws Exception {
        LoggerUtils.trace(ProductAction.class, traceID, "001", "delProductTactics start", systemID);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        String[] productIds = params.getString("productIds").split(",");
        if (productIds == null || productIds.length == 0) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "productIds不能为空", true);
        }
        String result = service.updateProductTacticsForFlag(productIds);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", result);
    }


    /**
     * 校验策略名称是否重复
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param params   请求参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/product/checkTacticsName", method = RequestMethod.POST)
    public ResponseResult checkTacticsName(String systemID, String traceID, @RequestBody JSONObject params) throws Exception {
        LoggerUtils.trace(TacticsRecordAction.class, traceID, "001", "checkTacticsName start", systemID);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        String productId = params.getString("productId");
        boolean isNull = productId == null && "".equals(productId);
        if (!isNull) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "订单策略ID不能为空", true);
        }
        if (!StringUtil.isNumeric(productId)) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "订单策略ID格式有误", true);
        }
        // 策略名称
        String tacticsName = params.getString("tacticsName");
        if (StringUtil.isEmpty(tacticsName)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "策略名称不能为空", true);
        }
        boolean exist = service.checkProductName(Integer.valueOf(productId), tacticsName.trim());
        String message = exist ? "订单策略名称可以使用" : "订单策略名称重复";
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, message, exist);
    }

    public String hasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return sb.toString();
        }
        return null;
    }
}