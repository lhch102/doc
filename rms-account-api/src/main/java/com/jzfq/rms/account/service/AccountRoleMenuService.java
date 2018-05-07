package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.AccountRoleMenuKey;

import java.util.HashMap;

public interface AccountRoleMenuService {

    int deleteByPrimaryKey(AccountRoleMenuKey key);

    int insert(AccountRoleMenuKey record);

    int insertSelective(AccountRoleMenuKey record);
}
