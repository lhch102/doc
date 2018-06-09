package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.ITacticsRecordService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 策略类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestController
@RequestMapping(value = "/inner")
public class TacticsRecordAction {

    @Autowired
    private ITacticsRecordService service;

    @RequestMapping(value = "/tacticsRecord/read", method = RequestMethod.POST)
    public ResponseResult getRecord(String systemID, String traceID, String params) throws Exception {
        LoggerUtils.trace(TacticsRecordAction.class, traceID, "001", "getRecord start", systemID);
        if (StringUtil.isEmpty(params)) {
            throw new BusinessException(-1, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });
        List<TacticsRecord> resultList = service.getAllByCondition(paramsMap);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
    }

    /**
     * 读取所有策略的内部版本号列表
     *
     * @param traceID
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsRecord/readList")
    public ResponseResult getTacticsIDList(String traceID, String params) throws Exception {
        if (StringUtils.isNotBlank(params)) {
            // Map<String, Object> paramsMap = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
            // });

            if (StringUtils.isNotBlank(traceID)) {
                List<String> resultList = service.getTacticsIDList(new HashMap());
                return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", resultList);
            } else {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "traceID不能为空", true);
            }
        } else {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
    }

    /**
     * 保存策略信息
     *
     * @param systemID 调用系统ID
     * @param traceID  链路ID
     * @param record   请求参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsRecord/save", method = RequestMethod.POST)
    public ResponseResult saveRecord(String systemID, String traceID, @RequestBody @Valid TacticsRecord record, BindingResult bindingResult) throws Exception {
        LoggerUtils.trace(TacticsRecordAction.class, traceID, "001", "saveRecord start", systemID);
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            /*for (ObjectError objectError : bindingResult.getAllErrors()) {
                sb.append(((FieldError) objectError).getField() + ":").append(objectError.getDefaultMessage());
            }*/
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb.toString(), true);
        }
        if (record == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        boolean b = service.checkTacticsName(record.getTacticsId(), record.getTacticsName());
        if (!b) {
            throw new BusinessException(ResponseCode.REPEAT, "策略名称不能重复", true);
        }
        service.insert(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "保存成功");
    }

    /**
     * 更改策略状态接口
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param params   请求参数
     * @return 返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsRecord/updateTacticsStatus", method = RequestMethod.POST)
    public ResponseResult updateTacticsStatus(String systemID, String traceID, @RequestBody JSONObject params) throws
            Exception {
        LoggerUtils.trace(TacticsRecordAction.class, traceID, "001", "updateTacticsStatus start", systemID);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "参数：traceID不能为空", true);
        }*/

        List<Integer> tacticsIds = (List) params.getJSONArray("tacticsId");

        if (tacticsIds == null || tacticsIds.size() == 0) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "参数：tacticsId不能为空", true);
        }
        String tacticsState = params.getString("tacticsState");

        if (StringUtil.isEmpty(tacticsState)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "参数：tacticsState不能为空", true);
        }
        String updater = params.getString("updater");
        if (StringUtil.isEmpty(updater)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "参数：updater不能为空", true);
        }
        int result = service.updateTacticsState(tacticsIds, tacticsState, updater);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用，成功修改" + result + "条", result);
    }

    /**
     * 策略分页数据（准入、风控、额度）
     *
     * @param systemID 调用系统ID
     * @param traceID  链路跟踪ID
     * @param record   请求参数
     * @return
     */
    @RequestMapping(value = "/tacticsRecord/getTacticsPage", method = RequestMethod.POST)
    public ResponseResult getTacticsPage(String systemID, String traceID, @RequestBody TacticsRecord record) throws
            Exception {
        LoggerUtils.trace(TacticsRecordAction.class, traceID, "001", "getTacticsPage start", systemID);

        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/

        PageData<TacticsRecord> tacticsRecordPageData = service.queryList(record);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", tacticsRecordPageData);
    }

    /**
     * 校验策略名称是否重复
     *
     * @param params 策略名称
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsRecord/checkTacticsName", method = RequestMethod.POST)
    public ResponseResult checkTacticsName(@RequestBody JSONObject params) throws Exception {

        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        LoggerUtils.trace(TacticsRecordAction.class, params.getString("traceID"), "001", "checkTacticsName start", params.getString("systemID"));
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        boolean exist = service.checkTacticsName(params.getInteger("tacticsId"), params.getString("tacticsName"));
        String message = exist ? "策略名称可以使用" : "策略名称重复";
        return new ResponseResult(params.getString("traceID"), ResponseCode.REQUEST_SUCCESS, message, exist);
    }

    /**
     * 生成策略编号
     *
     * @param systemID
     * @param traceID
     * @param params   策略类型；1：准入；2：风控；3：额度
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsRecord/generateTacticsNo", method = RequestMethod.POST)
    public ResponseResult generateTacticsNo(String systemID, String traceID, @RequestBody JSONObject params) throws
            Exception {
        LoggerUtils.trace(TacticsRecordAction.class, traceID, "001", "checkTacticsName start", systemID);
        if (params == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        Integer tacticsType = params.getInteger("tacticsType");
        if (StringUtil.isEmpty(tacticsType)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "策略类型不能为空", true);
        }
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        String tacticsNo = service.generateTacticsNo(tacticsType);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", tacticsNo);
    }

    /**
     * 获取跳过步骤分页列表
     *
     * @param systemID
     * @param traceID
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tacticsRecord/getSkipStepPage", method = RequestMethod.POST)
    public ResponseResult getSkipStepPage(String systemID, String traceID, @RequestBody JSONObject params) {
        try {
            if (params == null) {
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
            }
            //规则集编号
            String tacticsNo = params.getString("tacticsNo");
            //规则集名称
            String ruleSetsName = params.getString("ruleSetsName");
            Integer pageNum = params.getInteger("pageNum");
            Integer numPerPage = params.getInteger("numPerPage");
            if (StringUtil.isEmpty(tacticsNo)){
                throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "策略编号不能为空", true);
            }
            PageData<Map<String,Object>> SkipStepPageData = service.getSkipStepPage(tacticsNo,ruleSetsName,pageNum,numPerPage);
            return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", SkipStepPageData);
        } catch (BusinessException e) {
            e.printStackTrace();
            return new ResponseResult(traceID, ResponseCode.REQUEST_ERROR_PROGRAM_EXCEPTION, "程序异常");
        }
    }

    /**
     * 策略开启
     * @return
     */
    @RequestMapping(value = "/tactics/open", method = RequestMethod.GET)
    public ResponseResult open() {
        try {
                String tacticsId = "100014";
            String message = service.updateTacticsSwitch(true, tacticsId);
            if (message==null){
                return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "没有此策略");
            }
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "策略ID："+tacticsId+"自动通过开关设置为"+message);
        } catch (BusinessException e) {
            e.printStackTrace();
            return new ResponseResult(ResponseCode.REQUEST_ERROR_PROGRAM_EXCEPTION, "程序异常");
        }
    }

    /**
     * 策略关闭
     * @return
     */
    @RequestMapping(value = "/tactics/close", method = RequestMethod.GET)
    public ResponseResult close() {
        try {
                String tacticsId = "100014";
            String message = service.updateTacticsSwitch(false, tacticsId);
            if (message==null){
                return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "没有此策略");
            }
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "策略ID："+tacticsId+"自动通过开关设置为"+message);
        } catch (BusinessException e) {
            e.printStackTrace();
            return new ResponseResult(ResponseCode.REQUEST_ERROR_PROGRAM_EXCEPTION, "程序异常");
        }
    }
}