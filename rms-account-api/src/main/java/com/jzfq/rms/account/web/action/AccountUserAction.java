package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:43
 */
@RestController
@RequestMapping(value = "/user")
public class AccountUserAction {

    @Autowired
    private AccountUserService service;

    /**
     * 分页查询用户列表
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
    public ResponseResult queryUserList(@RequestBody AccountUser user) throws Exception {
        PageData<AccountUser> userPageData = service.queryUserList(user);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "正常调用", userPageData);
    }

    /**
     * 添加用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ResponseResult addUser() throws Exception {
        Map<String, Object> user = service.addUser();
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "正常调用", user);
    }

    /**
     * 逻辑删除用户
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseResult deleteUser(@RequestBody JSONObject params) throws Exception {
        if (params == null) {
            throw new BusinessException(-1, "业务参数不能为空", true);
        }
        List<String> userNos = (List) params.getJSONArray("userNos");
        if (userNos == null || userNos.size() == 0) {
            throw new BusinessException(-1, "参数：userNos不能为空", true);
        }
        String updateBy = params.getString("updateBy");
        if (StringUtil.isEmpty(updateBy)) {
            throw new BusinessException(-1, "参数：updateBy不能为空", true);
        }
        service.updateUserStatus(userNos, updateBy,null,"0");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "删除成功");
    }

    /**
     * 启用/停用
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    public ResponseResult updateUserStatus(@RequestBody JSONObject params) throws Exception {
        if (params == null) {
            throw new BusinessException(-1, "业务参数不能为空", true);
        }
        List<String> userNos = (List) params.getJSONArray("userNos");
        if (userNos == null || userNos.size() == 0) {
            throw new BusinessException(-1, "参数：userNos不能为空", true);
        }
        String updateBy = params.getString("updateBy");
        if (StringUtil.isEmpty(updateBy)) {
            throw new BusinessException(-1, "参数：updateBy不能为空", true);
        }
        Integer type = Integer.valueOf(params.getString("type"));
        if (type == null) {
            throw new BusinessException(-1, "参数：业务类型不能为空", true);
        }
        String message = null;
        String enableFlag = null;
        switch (type){
            case 0:
                message = "启用";
                enableFlag = EnumEnableFlag.ENABLE.getCode();
                break;
            case 1:
                message = "停用";
                enableFlag = EnumEnableFlag.UN_ENABLE.getCode();
                break;
        }
        service.updateUserStatus(userNos, updateBy,enableFlag,null);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, message+"成功");
    }
}
