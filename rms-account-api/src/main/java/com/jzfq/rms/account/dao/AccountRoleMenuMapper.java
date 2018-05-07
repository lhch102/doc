package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountRoleMenuKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleMenuMapper {
    int deleteByPrimaryKey(AccountRoleMenuKey key);

    int insert(AccountRoleMenuKey record);

    int insertSelective(AccountRoleMenuKey record);


}