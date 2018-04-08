package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountOperate;

public interface AccountOperateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountOperate record);

    int insertSelective(AccountOperate record);

    AccountOperate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountOperate record);

    int updateByPrimaryKey(AccountOperate record);
}