package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountSystem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountSystemMapper {
    int deleteByPrimaryKey(String systemNo);

    int insert(AccountSystem record);

    int insertSelective(AccountSystem record);

    AccountSystem selectByPrimaryKey(String systemNo);

    int updateByPrimaryKeySelective(AccountSystem record);

    int updateByPrimaryKey(AccountSystem record);

    List<Map<String,String>> getSystemList();

    String getMaxSystemNo();
}