package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.Extended.AccountRoleEditExtended;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.service.AccountOperateService;
import com.jzfq.rms.account.service.AccountRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 权限操作维护控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/4 15:29
 */
@RestController
@RequestMapping(value = "/account/operate")
public class AccountOperateAction {

    @Autowired
    private AccountOperateService accountOperateService;

    /**
     * 权限管理系统 修改角色接口
     *
     * @param operateParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upEnable", method = RequestMethod.POST)
    public ResponseResult remove(@RequestBody JSONObject operateParam) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg("upEnable 接口调用异常");
        responseResult.setCode(ReturnCode.ACTIVE_FAILURE.code());
        responseResult.setData("");

        if (operateParam != null && !operateParam.isEmpty()) {
            String menuNo = operateParam.getString("menuNo");
            String enable = operateParam.getString("enable");

            JSONArray operIds = operateParam.getJSONArray("operIds");
            HashMap<String, Object> operMaps = new HashMap<>();
            if (StringUtils.isNoneBlank(menuNo)) {
                operMaps.put("menuNo", menuNo);
            } else {
                responseResult.setMsg("menuNo不能为空");
                return responseResult;
            }
            if (operIds != null && operIds.size() > 0) {
                operMaps.put("operIds", operIds);
            } else {
                responseResult.setMsg("operIds不能为空");
                return responseResult;
            }
            if (StringUtils.isNotBlank(enable)) {
                operMaps.put("enable", enable);
            } else {
                responseResult.setMsg("enable不能为空");
                return responseResult;
            }
            if (operMaps.keySet().size() <= 0) {
                return responseResult;
            }
            accountOperateService.updateOperRelation(operMaps);
            responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
            responseResult.setData("");
            responseResult.setMsg("upEnable 接口调用成功");
        }
        return responseResult;
    }
}
