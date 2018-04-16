package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.AccountRole;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.AccountUserDeptKey;
import com.jzfq.rms.account.bean.AccountUserRoleKey;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.dao.AccountUserDeptMapper;
import com.jzfq.rms.account.dao.AccountUserMapper;
import com.jzfq.rms.account.dao.AccountUserRoleMapper;
import com.jzfq.rms.account.enums.EnumDelFlag;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.service.AccountDeptService;
import com.jzfq.rms.account.service.AccountRoleService;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.utils.DateUtils;
import com.jzfq.rms.account.utils.MD5Util;
import com.jzfq.rms.account.utils.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:46
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {

    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private GenerateObjectNoService generateObjectNoService;
    @Autowired
    private AccountDeptService accountDeptService;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private AccountUserDeptMapper userDeptMapper;
    @Autowired
    private AccountUserRoleMapper userRoleMapper;

    @Override
    public AccountUser queryByUserNameAndPassword(HashMap<String, Object> userMap) {
        return accountUserMapper.queryByUserNameAndPassword(userMap);
    }

    /**
     * 分页查询用户数据
     * @param user
     * @return
     */
    @Override
    public PageData<AccountUser> queryUserList(AccountUser user) {
        //删除flag（0：可用、1：已删除）
        user.setDelFlag(EnumDelFlag.DELETE_FLAG.getCode());
        PageParam<AccountUser> page = new PageParam<>(user, user.getPageNum(), user.getNumPerPage());
        List<AccountUser> userList = accountUserMapper.queryUserList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), userList);
    }

    /**
     * 启用/停用/逻辑删除共用
     * @param userNos
     * @param updateBy
     * @param enableFlag
     * @param delFlag
     */
    @Override
    public void updateUserStatus(List<String> userNos, String updateBy, String enableFlag, String delFlag) {
        AccountUser user = new AccountUser();
        for (String userNo : userNos) {
            user.setUserNo(userNo);
            if (delFlag != null) {
                user.setDelFlag(delFlag);
            } else {
                user.setEnableFlag(enableFlag);
            }
            user.setUpdateBy(updateBy);
            user.setUpdateDate(DateUtils.now());
            accountUserMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 添加用户
     * @return
     */
    @Override
    public Map<String, Object> addUser() {
        Map<String, Object> user = new HashMap<>(2);
        user.put("userNo", generateObjectNoService.generateUserNo());
        List<Map<String, Object>> dictList = new ArrayList<>();
        Map<String, Object> dict;
        dict = new LinkedHashMap<>();
        dict.put("label", EnumEnableFlag.ENABLE.getCode());
        dict.put("value", EnumEnableFlag.ENABLE.getMessage());
        dictList.add(dict);
        dict = new LinkedHashMap<>();
        dict.put("label", EnumEnableFlag.UN_ENABLE.getCode());
        dict.put("value", EnumEnableFlag.UN_ENABLE.getMessage());
        dictList.add(dict);
        user.put("enableFlag", dictList);
        return user;
    }

    @Value("${defaultPassword}")
    private String defaultPassword;

    /**
     * 重置密码
     * @param userNo
     * @param updateBy
     */
    @Override
    public void resetPassword(String userNo, String updateBy) {
        AccountUser user = new AccountUser();
        user.setUserNo(userNo);
        user.setPassword(MD5Util.MD5(defaultPassword + SaltUtil.saltStr));
        user.setUpdateBy(updateBy);
        user.setUpdateDate(DateUtils.now());
        accountUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 校验登录账户是否为空
     * @param userNo
     * @param loginName
     * @return
     */
    @Override
    public boolean checkLoginName(String userNo, String loginName) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        params.put("loginName", loginName);
        int count = accountUserMapper.checkLoginName(params);
        switch (count) {
            case 0:
                return true;
        }
        return false;
    }

    /**
     * 保存用户信息
     * @param accountUser
     */
    @Override
    public void insert(AccountUser accountUser) {
        accountUser.setCreateDate(DateUtils.now());
        accountUser.setDelFlag(EnumDelFlag.DELETE_FLAG.getCode());
        accountUserMapper.insertSelective(accountUser);
    }

    /**
     * 根据用户编号查询用户信息
     * @param userNo
     * @param systemNo
     * @return
     */
    @Override
    public AccountUser getUserInfo(String userNo,String systemNo) {
        AccountUser user = accountUserMapper.selectByPrimaryKey(userNo);
        List<Map<String, Object>> deptList = accountDeptService.getDeptListByUserNo(userNo);
        user.setBelongsToDeptList(deptList);
        AccountRole accountRole = new AccountRole();
        accountRole.setUserNo(userNo);
        accountRole.setSystemNo(systemNo);
        PageData<AccountRole> accountRolePageData = accountRoleService.queryRoleByUser(accountRole);
        user.setBelongsToRoleList(accountRolePageData);
        return user;
    }

    /**
     * 添加用户所属机构
     * @param userDeptKey
     * @return
     */
    @Override
    public int addBelongsToDept(AccountUserDeptKey userDeptKey) {
        return userDeptMapper.insertSelective(userDeptKey);
    }

    /**
     * 移除所属机构
     * @param userDeptKey
     * @return
     */
    @Override
    public int removeBelongsToDept(AccountUserDeptKey userDeptKey) {
        return userDeptMapper.deleteByPrimaryKey(userDeptKey);
    }

    /**
     * 添加用户角色
     * @param userRoleKey
     * @return
     */
    @Override
    public int addBelongsToRole(AccountUserRoleKey userRoleKey) {
        return userRoleMapper.insertSelective(userRoleKey);
    }

    /**
     * 移除用户角色
     * @param userRoleKey
     * @return
     */
    @Override
    public int removeBelongsToRole(AccountUserRoleKey userRoleKey) {
        return userRoleMapper.deleteByPrimaryKey(userRoleKey);
    }

    @Override
    public AccountUser queryByLoginName(HashMap<String, Object> userName) {
        return accountUserMapper.queryByLoginName(userName);
    }
}
