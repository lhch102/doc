package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleMapper {
    int deleteByPrimaryKey(String roleNo);

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

    AccountRole selectByPrimaryKey(String roleNo);

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);

    /**
     * 获取最大角色编号
     * @return
     */
    String getMaxRoleNo();
}