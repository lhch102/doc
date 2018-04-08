package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.common.PageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AccountDicTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountDicType record);

    int insertSelective(AccountDicType record);

    AccountDicType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountDicType record);

    int updateByPrimaryKey(AccountDicType record);

    /**
     * 校验字典名称是否重复
     *
     * @param typeName,id
     * @return
     */

    int checkTypeName(@Param("typeName") String typeName, @Param("id") String id);

    /**
     * 分页查询字典数据
     *
     * @param params
     * @return
     */
    List<AccountDicType> findDictions(PageParam<AccountDicType> params);
}