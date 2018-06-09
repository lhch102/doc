package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.product.bean.*;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.CustomerTypeService;
import com.jzfq.rms.product.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 客群策略控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/9 16:14
 */
@RestController
@RequestMapping(value = "/inner")
public class CustomerTypeAction {

    private final static Logger logger = LoggerFactory.getLogger(CustomerTypeAction.class);

    @Autowired
    private CustomerTypeService service;

    /**
     * 将最新策略ID添加至缓存
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/addRedisKQID", method = RequestMethod.POST)
    public ResponseResult addRedisKQID(@RequestBody(required = false) JSONObject params) throws BusinessException {
        logger.info("入参：" + params);
        Map<String, Object> param = new HashMap<>(2);
        int size;
        if (params == null || params.size() < 1) {
            size = service.addRedisKQID(param);
        } else {
            Integer customerTypeId = params.getInteger("customerTypeId");
            String tacticsKQId = params.getString("tacticsKQId");
            if (customerTypeId == null) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "参数：customerTypeId不能为空", true);
            }
            if (StringUtil.isEmpty(tacticsKQId)) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "参数：tacticsKQId不能为空", true);
            }
            param.put("customerTypeId", customerTypeId);
            param.put("tacticsKQId", tacticsKQId);
            size = service.addRedisKQID(param);
        }
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "成功添加" + size + "条客群策略ID至redis缓存");

    }

    /**
     * 将客群策略添加至缓存
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/addTacticsKQ", method = RequestMethod.GET)
    public ResponseResult addTacticsKQ() throws BusinessException {
        int size;
        size = service.addTacticsKQ();
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "成功添加" + size + "条客群策略至redis缓存");

    }

    /**
     * 分页查询客群信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/queryTacticsKQPage", method = RequestMethod.POST)
    public ResponseResult queryTacticsKQPage(@RequestBody CustomerType record) throws BusinessException {
        logger.info("入参" + record);
        if (record == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        PageData<CustomerType> pageData = service.queryList(record);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "正常调用", pageData);
    }

    /**
     * 分页查询配置手机列表
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/queryMobilePage", method = RequestMethod.POST)
    public ResponseResult queryMobilePage(@RequestBody ConfigMobile mobile) throws BusinessException {
        logger.info("入参" + mobile);
        if (mobile == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        PageData<ConfigMobile> pageData = service.queryMobilePage(mobile);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "正常调用", pageData);
    }

    /**
     * 分页查询F码列表
     * @param registCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/queryRegistCodePage", method = RequestMethod.POST)
    public ResponseResult queryRegistCodePage(@RequestBody RegistCode registCode) throws BusinessException {
        logger.info("入参" + registCode);
        if (registCode == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        PageData<RegistCode> pageData = service.queryRegistCodePage(registCode);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "正常调用", pageData);
    }

    /**
     * 分页查询经纬度列表
     * @param latitudeLongitude
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/querylatitudeLongitudePage", method = RequestMethod.POST)
    public ResponseResult querylatitudeLongitudePage(@RequestBody LatitudeLongitude latitudeLongitude) throws BusinessException {
        logger.info("入参" + latitudeLongitude);
        if (latitudeLongitude == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        PageData<LatitudeLongitude> pageData = service.querylatitudeLongitudePage(latitudeLongitude);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "正常调用", pageData);
    }

    /**
     * 添加客群加载默认项
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/loadTacticsKQ", method = RequestMethod.GET)
    public ResponseResult loadTacticsKQ() throws BusinessException {
        logger.info("添加客群接口");
        Map<String, Object> user = service.loadTacticsKQ();
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "正常调用", user);

    }

    /**
     * 客群策略编辑
     *
     * @param customerTypeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/editTacticsKQ", method = RequestMethod.GET)
    public ResponseResult editTacticsKQ(@RequestParam String customerTypeId) throws BusinessException {
        logger.info("入参：" + customerTypeId);
        if (customerTypeId == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "customerTypeId不能为空", true);
        }
        if (!StringUtil.isNumeric(customerTypeId)) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "参数类型应为数字", true);
        }
        CustomerType response = service.editTacticsKQ(Integer.valueOf(customerTypeId));
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "正常调用", response);

    }

    /**
     * 校验客群名称是否重复
     *
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/checkTacticsKQName", method = RequestMethod.POST)
    public ResponseResult checkTacticsKQName(@RequestBody JSONObject params) throws BusinessException {
        logger.info("入参：" + params);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        String tacticskqId = params.getString("tacticskqId");
        // 客群名称
        String customerTypeName = params.getString("customerTypeName").trim();
        if (StringUtil.isEmpty(customerTypeName)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "策略名称不能为空", true);
        }
        boolean exist = service.checkTacticsKQName(tacticskqId, customerTypeName);
        String message = exist ? "客群策略名称可以使用" : "客群策略名称重复";
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, message, exist);

    }

    /**
     * 添加手机号
     *
     * @param configMobile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/addMobile", method = RequestMethod.PUT)
    public ResponseResult addMobile(@Valid @RequestBody ConfigMobile configMobile, BindingResult bindingResult) throws BusinessException {
        logger.info("入参：", configMobile.toString());
        String sb = hasErrors(bindingResult);
        if (sb != null) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb, true);
        }
        service.addMobile(configMobile);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "添加成功");
    }

    /**
     * 添加经纬度
     *
     * @param latitudeLongitude
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/addLatitudeLongitude", method = RequestMethod.PUT)
    public ResponseResult addLatitudeLongitude(@Valid @RequestBody LatitudeLongitude latitudeLongitude, BindingResult bindingResult) throws BusinessException {
        logger.info("入参：", latitudeLongitude.toString());
        String sb = hasErrors(bindingResult);
        if (sb != null) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb, true);
        }
        service.addLatitudeLongitude(latitudeLongitude);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "添加成功");
    }

    /**
     * 添加F码
     *
     * @param registCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/addRegistCode", method = RequestMethod.PUT)
    public ResponseResult addRegistCode(@Valid @RequestBody RegistCode registCode, BindingResult bindingResult) throws BusinessException {
        logger.info("入参：", registCode.toString());
        String sb = hasErrors(bindingResult);
        if (sb != null) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb, true);
        }
        service.addRegistCode(registCode);
        return new ResponseResult("", ResponseCode.REQUEST_SUCCESS, "添加成功");
    }

    /**
     * 移除手机号/F码/经纬度
     * @param params   type  0:移除手机号;1:移除F码;2:移除经纬度
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsKQ/removeObject", method = RequestMethod.DELETE)
    public ResponseResult removeObject(@RequestBody JSONObject params) throws BusinessException {
        logger.info("入参：" + params);
        if (StringUtil.isEmpty(params)) {
            return new ResponseResult(null, ResponseCode.BUSINESS_PARAM_ERROR, "参数不能为空");
        }
        List<Object> idObject = (List) params.getJSONArray("ids");
        if (idObject == null || idObject.size() == 0) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "id不能为空", true);
        }
        List<Integer> ids = new ArrayList<>();
        for (Object id : idObject) {
            if (!(id instanceof Integer)) {
                throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "参数：id必须为数字类型", true);
            }
            ids.add(Integer.valueOf(id.toString()));
        }
        String type = params.getString("type");
        if (StringUtil.isEmpty(type)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "type不能为空", true);
        }
        if (!StringUtil.isNumeric(type)) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "参数type格式不正确", true);
        }
        service.removeObject(Integer.valueOf(type),ids);
        return new ResponseResult(null, ResponseCode.REQUEST_SUCCESS, "移除成功");
    }

    /**
     * 更新有效期
     *
     * @return
     */
    @RequestMapping(value = "/tacticsKQ/updateValidityPeriod", method = RequestMethod.POST)
    public ResponseResult updateValidityPeriod(@RequestBody JSONObject params) throws BusinessException {
        try {
            if (params == null) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
            }
            String type = params.getString("type");
            List<Object> idObject = (List) params.getJSONArray("ids");
            if (idObject == null || idObject.size() == 0) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "ids不能为空", true);
            }
            List<Integer> ids = new ArrayList<>();
            for (Object id : idObject) {
                if (!(id instanceof Integer)) {
                    throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "参数id格式不正确", true);
                }
                ids.add(Integer.valueOf(id.toString()));
            }
            String validityPeriod = params.getString("validityPeriod");
            if (StringUtil.isEmpty(type)) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "type不能为空", true);
            }
            if (StringUtil.isEmpty(validityPeriod)) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "有效期不能为空", true);
            }
            if (!StringUtil.isNumeric(type)) {
                throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "参数type格式不正确", true);
            }
            //校验日期
            String regEx = "^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(validityPeriod);
            boolean rs = matcher.matches();
            if (!rs) {
                throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, "截止有效期格式不正确", true);
            }
            int size = service.updateValidityPeriod(Integer.valueOf(type), ids, validityPeriod);
            logger.info("成功更新" + size + "条");
            return new ResponseResult(null, ResponseCode.REQUEST_SUCCESS, "更新成功");
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("日期转换错误");
            return new ResponseResult(null, ResponseCode.REQUEST_ERROR_PROGRAM_EXCEPTION, "日期转换错误");
        }
    }

    /**
     * 保存客群策略信息
     * @param customerType
     * @param bindingResult
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/tacticsKQ/saveTacticsKQ", method = RequestMethod.POST)
    public ResponseResult saveTacticsKQ(@Valid @RequestBody CustomerType customerType, BindingResult bindingResult) throws BusinessException {
        String sb = hasErrors(bindingResult);
        if (sb != null) {
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb, true);
        }
        if (customerType == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        boolean b = service.checkTacticsKQName(customerType.getTacticskqId(), customerType.getCustomerType().trim());
        if (!b) {
            throw new BusinessException(ResponseCode.REPEAT, "客群策略名称重复", true);
        }
        int i= service.saveTacticsKQ(customerType);
        logger.info("成功保存客群策略信息"+i+"条");
        return new ResponseResult(null, ResponseCode.REQUEST_SUCCESS, "保存成功");
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
