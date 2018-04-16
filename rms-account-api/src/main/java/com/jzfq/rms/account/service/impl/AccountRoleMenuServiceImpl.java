package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.AccountRoleMenuKey;
import com.jzfq.rms.account.dao.AccountRoleMenuMapper;
import com.jzfq.rms.account.service.AccountRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional(readOnly = false)
@Service
public class AccountRoleMenuServiceImpl implements AccountRoleMenuService {

    @Autowired
    private AccountRoleMenuMapper accountRoleMenuMapper;

    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(AccountRoleMenuKey key) {
        return accountRoleMenuMapper.deleteByPrimaryKey(key);
    }

    @Transactional(readOnly = false)
    @Override
    public int insert(AccountRoleMenuKey accountRoleMenuKey) {
        return accountRoleMenuMapper.insert(accountRoleMenuKey);
    }

    @Transactional(readOnly = false)
    @Override
    public int insertSelective(AccountRoleMenuKey record) {
        return accountRoleMenuMapper.insertSelective(record);
    }

}
