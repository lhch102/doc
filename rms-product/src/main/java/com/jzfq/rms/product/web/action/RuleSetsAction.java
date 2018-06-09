package com.jzfq.rms.product.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.product.bean.ResponseResult;
import com.jzfq.rms.product.bean.RuleSets;
import com.jzfq.rms.product.constant.ResponseCode;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.service.IRuleSetsService;
import com.jzfq.rms.product.utils.LoggerUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  规则集处理类
 *  @author 大连桔子分期科技有限公司
 *  @date 2018/1/29 16:44
 */
@RestController
@RequestMapping(value = "/inner")
public class RuleSetsAction {


    @Autowired
    private IRuleSetsService setsService;

    /**
     * 根据策略编号获取规则集信息
     * @param systemID  调用系统ID
     * @param traceID   链路跟踪ID
     * @param params    请求参数
     * @return          返回结果
     * @throws Exception
     */
    @RequestMapping(value = "/ruleSets/getRuleSetsList", method= RequestMethod.POST)
    public ResponseResult getRuleList(String systemID, String traceID, @RequestBody JSONObject params) throws Exception {
        LoggerUtils.trace(RuleInfoAction.class,traceID,"001","getRecord start",systemID);
        /*if (StringUtil.isEmpty(traceID)) {
            throw new BusinessException(-1, "traceID不能为空", true);
        }*/
        if (MapUtils.isEmpty(params)) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        String tacticsNo = params.getString("tacticsNo");
        if (StringUtil.isEmpty(tacticsNo)){
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "tacticsNo不能为空", true);
        }
        List<RuleSets> resultList = setsService.getRuleList(tacticsNo);
        Map<String,List> map = new HashMap<>(2);
        map.put("list",resultList);
        return new ResponseResult(traceID, ResponseCode.REQUEST_SUCCESS, "正常调用", map);
    }

}
