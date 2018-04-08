package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountMenuOperateKey;

public interface AccountMenuOperateMapper {
    int deleteByPrimaryKey(AccountMenuOperateKey key);

    int insert(AccountMenuOperateKey record);

    int insertSelective(AccountMenuOperateKey record);
}