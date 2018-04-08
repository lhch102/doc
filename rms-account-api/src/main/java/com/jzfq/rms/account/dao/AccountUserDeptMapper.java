package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountUserDeptKey;

public interface AccountUserDeptMapper {
    int deleteByPrimaryKey(AccountUserDeptKey key);

    int insert(AccountUserDeptKey record);

    int insertSelective(AccountUserDeptKey record);
}