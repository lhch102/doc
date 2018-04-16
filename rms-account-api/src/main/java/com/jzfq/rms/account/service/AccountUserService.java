package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.AccountUserDeptKey;
import com.jzfq.rms.account.bean.AccountUserRoleKey;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.exception.BusinessException;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:44
 */

public interface AccountUserService {

    /**
     * 分页查询用户数据
     *
     * @param user
     * @return
     */
    PageData<AccountUser> queryUserList(AccountUser user)throws BusinessException;

    public AccountUser queryByUserNameAndPassword(HashMap<String, Object> userMap)throws BusinessException;

    /**
     * 启用/停用
     *
     * @param userNos
     * @param updateBy
     * @param enableFlag
     * @param delFlag
     */
    void updateUserStatus(List<String> userNos, String updateBy, String enableFlag,String delFlag)throws BusinessException;

    /**
     * 添加用户
     *
     * @return
     */
    Map<String, Object> addUser()throws BusinessException;

    public AccountUser queryByLoginName(HashMap<String, Object> userMap)throws BusinessException;


    /**
     * 重置密码
     * @param userNo
     * @param updateBy
     */
    void resetPassword(String userNo, String updateBy)throws BusinessException;

    /**
     * 校验登录账户是否为空
     * @param userNo
     * @param loginName
     * @return true:可以使用；false名称重复，不可使用
     */
    boolean checkLoginName(String userNo, String loginName);

    /**
     * 保存用户信息
     * @param accountUser
     */
    void insert(AccountUser accountUser);

    /**
     * 根据用户编号查询用户信息
     *
     * @param userNo
     * @param systemNo
     * @return
     */
    AccountUser getUserInfo(String userNo,String systemNo);

    /**
     * 添加用户所属机构
     * @param userDeptKey
     * @return
     */
    int addBelongsToDept(AccountUserDeptKey userDeptKey);

    /**
     * 移除所属机构
     * @param userDeptKey
     * @return
     */
    int removeBelongsToDept(AccountUserDeptKey userDeptKey);

    /**
     * 添加用户角色
     * @param userRoleKey
     * @return
     */
    int addBelongsToRole(AccountUserRoleKey userRoleKey);

    /**
     * 移除用户角色
     * @param userRoleKey
     * @return
     */
    int removeBelongsToRole(AccountUserRoleKey userRoleKey);
}
