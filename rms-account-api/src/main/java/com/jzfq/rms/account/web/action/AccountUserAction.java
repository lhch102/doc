package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.AccountUserDeptKey;
import com.jzfq.rms.account.bean.AccountUserRoleKey;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 用户控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:43
 */
@RestController
@RequestMapping(value = "/user")
public class AccountUserAction {

    private final static Logger logger = LoggerFactory.getLogger(AccountRoleAction.class);

    @Autowired
    private AccountUserService service;

    /**
     * 分页查询用户列表
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
    public ResponseResult queryUserList(@RequestBody AccountUser user) throws Exception {
        logger.info("参数：" + user);
        PageData<AccountUser> userPageData = service.queryUserList(user);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "正常调用", userPageData);
    }

    /**
     * 添加用户
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ResponseResult addUser() throws Exception {
        logger.info("添加用户接口");
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
        logger.info("参数：", params);
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
        service.updateUserStatus(userNos, updateBy, null, "0");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "删除成功");
    }

    /**
     * 启用/停用
     * @param params type:0 启用
     *               type:1 停用
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    public ResponseResult updateUserStatus(@RequestBody JSONObject params) throws Exception {
        logger.info("参数：", params);
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
        switch (type) {
            case 0:
                message = "启用";
                enableFlag = EnumEnableFlag.ENABLE.getCode();
                break;
            case 1:
                message = "停用";
                enableFlag = EnumEnableFlag.UN_ENABLE.getCode();
                break;
        }
        service.updateUserStatus(userNos, updateBy, enableFlag, null);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, message + "成功");
    }

    /**
     * 重置密码
     * 重置默认密码【111111】
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseResult resetPassword(@Valid @RequestBody JSONObject params) throws Exception {
        logger.info("参数：", params);
        if (params == null) {
            throw new BusinessException(-1, "业务参数不能为空", true);
        }
        String userNo = params.getString("userNo").trim();

        if (userNo == null || "".equals(userNo)) {
            throw new BusinessException(-1, "参数：userNo不能为空", true);
        }
        String updateBy = params.getString("updateBy");
        if (StringUtil.isEmpty(updateBy)) {
            throw new BusinessException(-1, "参数：updateBy不能为空", true);
        }
        service.resetPassword(userNo, updateBy);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "重置成功");
    }

    /**
     * 保存用户信息
     * @param accountUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ResponseResult saveUser(@Valid @RequestBody AccountUser accountUser, BindingResult bindingResult) throws Exception {
        logger.info("参数：", accountUser);
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            throw new BusinessException(ResponseCode.RESPONSE_CODE_ILLEGAL_ARGUMENT, sb.toString(), true);
        }
        if (accountUser == null) {
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, "业务参数不能为空", true);
        }
        boolean exist = service.checkLoginName(accountUser.getUserNo(), accountUser.getLoginName());
        if (!exist) {
            throw new BusinessException(ResponseCode.REQUEST_EXIST_DICNAME, "登录账户重复", true);
        }
        service.insert(accountUser);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "保存成功");
    }

    /**
     * 根据用户编号查询用户信息
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public ResponseResult getUserInfo(@RequestBody JSONObject params) throws Exception {
        logger.info("参数：", params);
        if (params == null) {
            throw new BusinessException(-1, "业务参数不能为空", true);
        }
        String userNo = params.getString("userNo").trim();
        String systemNo = params.getString("systemNo").trim();
        if (StringUtil.isEmpty(userNo)) {
            throw new BusinessException(-1, "用户编号不能为空", true);
        }
        if (StringUtil.isEmpty(systemNo)) {
            throw new BusinessException(-1, "系统编号不能为空", true);
        }
        AccountUser userInfo = service.getUserInfo(userNo, systemNo);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "请求成功", userInfo);
    }

    /**
     * 校验策略名称是否重复
     * @param params 策略名称
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkLoginName", method = RequestMethod.POST)
    public ResponseResult checkLoginName(@RequestBody JSONObject params) throws Exception {
        logger.info("参数：", params);
        if (params == null) {
            throw new BusinessException(-1, "业务参数不能为空", true);
        }
        boolean exist = service.checkLoginName(params.getString("userNo"), params.getString("loginName"));
        String message = exist ? "登录账户可以使用" : "登录账户重复";
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, message, exist);
    }

    /**
     * 添加所属机构
     * @param userDeptKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addBelongsToDept", method = RequestMethod.POST)
    public ResponseResult addBelongsToDept(@Valid @RequestBody AccountUserDeptKey userDeptKey, BindingResult bindingResult) throws Exception {
        logger.info("参数：", userDeptKey);
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, sb.toString(), true);
        }
        int num = service.addBelongsToDept(userDeptKey);
        logger.info("成功添加【" + num + "】条所属机构");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "添加成功");
    }

    /**
     * 移除所属机构
     * @param userDeptKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/removeBelongsToDept", method = RequestMethod.POST)
    public ResponseResult removeBelongsToDept(@Valid @RequestBody AccountUserDeptKey userDeptKey, BindingResult bindingResult) throws Exception {
        logger.info("参数：", userDeptKey);
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, sb.toString(), true);
        }
        int num = service.removeBelongsToDept(userDeptKey);
        logger.info("成功移除【" + num + "】条所属机构");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "移除成功");
    }

    /**
     * 添加用户角色
     * @param userRoleKey
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addBelongsToRole", method = RequestMethod.POST)
    public ResponseResult addBelongsToRole(@Valid @RequestBody AccountUserRoleKey userRoleKey, BindingResult bindingResult) throws Exception {
        logger.info("参数：", userRoleKey);
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            throw new BusinessException(ResponseCode.BUSINESS_PARAM_ERROR, sb.toString(), true);
        }
        int num = service.addBelongsToRole(userRoleKey);
        logger.info("成功添加【" + num + "】条用户角色");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "添加成功");
    }

    /**
     * 移除用户角色
     * @param userRoleKey
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/removeBelongsToRole", method = RequestMethod.POST)
    public ResponseResult removeBelongsToRole(@Valid @RequestBody AccountUserRoleKey userRoleKey, BindingResult bindingResult) throws Exception {
        logger.info("参数：", userRoleKey);
        int num = service.removeBelongsToRole(userRoleKey);
        logger.info("成功移除【" + num + "】条用户角色");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "添加成功");
    }
}
