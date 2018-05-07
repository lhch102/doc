package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountRole;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.PageParam;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @return
     */
    String getMaxRoleNo();

    public List<AccountRole> queryRoleList(PageParam<AccountRole> page);

    public void updateEnableByPrimaryKey(AccountRole accountRole);

    public void updateEnable(HashMap<String ,Object> hashMap);

    public void updateDelFlag(HashMap<String ,Object> hashMap);

    public AccountRole queryByRoleName(HashMap<String ,Object> hashMap);

    /**
     * 根据用户分页查询角色列表
     * @param page
     * @return
     */
    List<Map<String,Object>> queryRoleByUser(PageParam<AccountRole> page);
}