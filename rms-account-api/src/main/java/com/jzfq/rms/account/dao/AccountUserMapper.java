package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.PageParam;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AccountUserMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(AccountUser record);

    int insertSelective(AccountUser record);

    AccountUser selectByPrimaryKey(String userNo);

    int updateByPrimaryKeySelective(AccountUser record);

    int updateByPrimaryKey(AccountUser record);

    /**
     * 分页查询用户数据
     *
     * @param page
     * @return
     */
    List<AccountUser> queryUserList(PageParam<AccountUser> page);

    /**
     * 获取最大用户编号
     * @return
     */
    String getMaxUserNo();

    public AccountUser queryByUserNameAndPassword(HashMap<String, Object> userMap);
}