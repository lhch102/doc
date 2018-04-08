package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountDept;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDeptMapper {
    int deleteByPrimaryKey(String deptNo);

    int insert(AccountDept record);

    int insertSelective(AccountDept record);

    AccountDept selectByPrimaryKey(String deptNo);

    int updateByPrimaryKeySelective(AccountDept record);

    int updateByPrimaryKey(AccountDept record);

    /**
     * 获取最大机构编号
     * @return
     */
    String getMaxDeptNo();
}