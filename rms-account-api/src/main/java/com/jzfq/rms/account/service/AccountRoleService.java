package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.bean.AccountRole;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.Extended.AccountRoleEditExtended;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.exception.BusinessException;

import java.util.HashMap;

public interface AccountRoleService {
    public AccountRole selectByPrimaryKey(String roleNo);

    public AccountRoleEditExtended getRoleOperatesAll(String roleNo);

    int updateByPrimaryKeySelective(AccountRole record);

    PageData<AccountRole> queryRoleList(AccountRole role) throws BusinessException;

    public void updateEnableByPrimaryKey(AccountRole accountRole);

    int insert(AccountRole record) throws BusinessException;

    public void updateEnable(HashMap<String, Object> hashMap) throws BusinessException;

    public void updateDelFlag(HashMap<String, Object> hashMap);

    public AccountRole queryByRoleName(HashMap<String, Object> hashMap);

    /**
     * 根据用户分页查询角色列表
     * @param accountRole
     * @return
     */
    PageData<AccountRole> queryRoleByUser(AccountRole accountRole);
    public void save(AccountRole record) throws Exception;
}
