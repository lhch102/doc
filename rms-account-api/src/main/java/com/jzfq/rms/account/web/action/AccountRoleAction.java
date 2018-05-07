package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountRole;
import com.jzfq.rms.account.bean.Extended.AccountRoleEditExtended;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountRoleMenuService;
import com.jzfq.rms.account.service.AccountRoleService;
import com.jzfq.rms.account.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限维护控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/4 15:29
 */
@Transactional(readOnly = false)
@RestController
@RequestMapping(value = "/account/role")
public class AccountRoleAction {

    private final static Logger logger = LoggerFactory.getLogger(AccountRoleAction.class);
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private AccountRoleMenuService accountRoleMenuService;

    /**
     * 权限管理系统 修改角色接口
     *
     * @param roleParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyLoad", method = RequestMethod.POST)
    public ResponseResult modifyRole(@RequestBody JSONObject roleParam) throws Exception {
        logger.info("参数：" + roleParam);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg("权限修改接口调用异常");
        responseResult.setCode(ReturnCode.ACTIVE_FAILURE.code());
        responseResult.setData("");

        if (roleParam != null && !roleParam.isEmpty()) {
            String roleNo = roleParam.getString("roleNo");
            AccountRoleEditExtended accountRoleEditExtended = accountRoleService.getRoleOperatesAll(roleNo);
            responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
            responseResult.setData(accountRoleEditExtended);
            responseResult.setMsg("权限修改接口调用成功");
        }
        return responseResult;
    }

    /**
     * 权限管理系统  新增角色接口
     *
     * @param roleParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addLoad", method = RequestMethod.POST)
    public ResponseResult addRole(@RequestBody JSONObject roleParam) throws Exception {

        logger.info("参数：" + roleParam);


        ResponseResult responseResult = new ResponseResult();
        String roleNo = roleParam.getString("roleNo");
        AccountRoleEditExtended accountRoleEditExtended = accountRoleService.getRoleOperatesAll(roleNo);
        responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
        responseResult.setData(accountRoleEditExtended);
        responseResult.setMsg("权限新增接口调用成功");

        return responseResult;
    }

    /**
     * 角色启用停用
     *
     * @param roleParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public ResponseResult enable(@RequestBody JSONObject roleParam) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(500);
        logger.info("参数：" + roleParam);
        List<String> roleNoList = new ArrayList<String>();
        try {
            if (roleParam == null || roleParam.isEmpty()) {
                responseResult.setMsg("参数不能为空");
                return responseResult;
            }
            String enableFlag = roleParam.getString("enableFlag");
            JSONArray roleNos = roleParam.getJSONArray("roleNos");
            for (int i = 0; i < roleNos.size(); i++) {
                roleNoList.add(roleNos.get(i).toString());
            }
            try {
                HashMap<String, Object> mapParms = new HashMap<>();
                mapParms.put("enableFlag", enableFlag);
                mapParms.put("roleNos", roleNoList);
                accountRoleService.updateEnable(mapParms);
                responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
                responseResult.setData("");
                responseResult.setMsg("接口调用成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResult;
    }


    /**
     * 权限管理系统  查询所有角色列表
     *
     * @param accountRole
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public ResponseResult findPage(@RequestBody AccountRole accountRole) throws Exception {
        logger.info("参数：" + accountRole);
        PageData<AccountRole> rolePageData = accountRoleService.queryRoleList(accountRole);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
        responseResult.setData(rolePageData);
        responseResult.setMsg("调用接口成功");
        return responseResult;
    }

    /**
     * 权限管理系统  保存角色基本信息
     *
     * @param accountRole
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(@RequestBody AccountRole accountRole) {
        logger.info("参数：" + accountRole);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ReturnCode.ACTIVE_FAILURE.code());

        try {
            if (accountRole != null) {
                accountRoleService.save(accountRole);
                responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
                responseResult.setData("");
                responseResult.setMsg("调用接口成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setCode(ReturnCode.ACTIVE_FAILURE.code());
            responseResult.setData(e);
            responseResult.setMsg("调用接口失败");
        }
        return responseResult;
    }

    /**
     * 角色启用停用
     *
     * @param roleParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleflag", method = RequestMethod.POST)
    public ResponseResult deleflag(@RequestBody JSONObject roleParam) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(500);
        logger.info("参数：" + roleParam);
        List<String> roleNoList = new ArrayList<String>();
        try {
            if (roleParam == null || roleParam.isEmpty()) {
                responseResult.setMsg("参数不能为空");
                return responseResult;
            }
            String delFlag = roleParam.getString("delFlag");
            JSONArray roleNos = roleParam.getJSONArray("roleNos");
            for (int i = 0; i < roleNos.size(); i++) {
                roleNoList.add(roleNos.get(i).toString());
            }
            try {
                HashMap<String, Object> mapParms = new HashMap<>();
                mapParms.put("delFlag", delFlag);
                mapParms.put("roleNos", roleNoList);
                accountRoleService.updateDelFlag(mapParms);
                responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
                responseResult.setData("");
                responseResult.setMsg("接口调用成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    /**
     * 校验角色名称
     *
     * @param roleParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkRoleName", method = RequestMethod.POST)
    public ResponseResult checkRoleName(@RequestBody JSONObject roleParam) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(500);
        logger.info("参数：" + roleParam);
        String roleName = roleParam.getString(roleParam.getString("roleName"));
        try {
            HashMap<String, Object> roleMap = new HashMap<String, Object>();
            roleMap.put("name", roleName);
            AccountRole accountRole = accountRoleService.queryByRoleName(roleMap);
            if (accountRole != null) {
                responseResult.setMsg("角色名称重复");
                responseResult.setData(false);
            } else {
                responseResult.setData(true);
                responseResult.setCode(200);
                responseResult.setMsg("名称可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    /**
     * 根据用户分页查询角色列表
     * @param accountRole
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryRoleByUser", method = RequestMethod.POST)
    public ResponseResult queryRoleByUser(@Valid @RequestBody AccountRole accountRole, BindingResult bindingResult) throws Exception {
        logger.info("参数：" + accountRole);
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), sb.toString());
        }
        PageData<AccountRole> rolePageData = accountRoleService.queryRoleByUser(accountRole);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ReturnCode.ACTIVE_SUCCESS.code());
        responseResult.setData(rolePageData);
        responseResult.setMsg("调用接口成功");
        return responseResult;
    }
}
