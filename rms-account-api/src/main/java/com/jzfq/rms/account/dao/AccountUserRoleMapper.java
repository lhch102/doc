package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountUserRoleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRoleMapper {
    int deleteByPrimaryKey(AccountUserRoleKey key);

    int insert(AccountUserRoleKey record);

    int insertSelective(AccountUserRoleKey record);
}