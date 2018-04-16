package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountDept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取机构列表
     *
     * @param debt
     * @return
     */

    List<AccountDept> getDeptList(AccountDept debt);


    /**
     * 启用/停用
     *
     * @param type
     * @return
     */

    int isUsing(@Param("type") String type, @Param("list") List<String> list);

    /**
     * 根据用户编号查询用户所属机构
     * @param userNo
     * @return
     */
    List<Map<String, Object>> getDeptListByUserNo(String userNo);
}