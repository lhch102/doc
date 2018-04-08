package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMenuMapper {
    int deleteByPrimaryKey(String menuNo);

    int insert(AccountMenu record);

    int insertSelective(AccountMenu record);

    AccountMenu selectByPrimaryKey(String menuNo);

    int updateByPrimaryKeySelective(AccountMenu record);

    int updateByPrimaryKey(AccountMenu record);

    /**
     * 获取最大菜单编号
     * @return
     */
    String getMaxMenuNo();
}