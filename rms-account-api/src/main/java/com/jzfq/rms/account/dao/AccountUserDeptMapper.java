package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountUserDeptKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.util.List;

@Repository
public interface AccountUserDeptMapper {
    int deleteByPrimaryKey(AccountUserDeptKey key);

    int insert(AccountUserDeptKey record);

    int insertSelective(AccountUserDeptKey record);


    /**
     * 获取旧的机构关联列表
     *
     * @param deptNo
     * @return
     */

    List<String> queryOldUserDeptList(@Param("deptNo") String deptNo);




}