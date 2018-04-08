package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.PageData;

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
     * @param user
     * @return
     */
    PageData<AccountUser> queryUserList(AccountUser user);

    public AccountUser queryByUserNameAndPassword(HashMap<String,Object> userMap);

    /**
     * 启用/停用
     * @param userNos
     * @param updateBy
     * @param enableFlag
     * @param delFlag
     */
    void updateUserStatus(List<String> userNos, String updateBy, String enableFlag,String delFlag);

    /**
     * 添加用户
     * @return
     */
    Map<String, Object> addUser();
}
