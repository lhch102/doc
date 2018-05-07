package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountDic;

import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.utils.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDicMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccountDic record);

    int insertSelective(AccountDic record);

    AccountDic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountDic record);

    int updateByPrimaryKey(AccountDic record);

    /**
     * 分页查询字典数据
     *
     * @param params
     * @return
     */
    List<AccountDic> findDictions(PageParam<AccountDic> params);


    /**
     * 根据字典类型查询字典数据
     *
     * @param type
     * @return
     */
    List<AccountDic> queryDicListByType(String type);


    /**
     * 启用/停用
     *
     * @param type
     * @return
     */

    int isUsing(@Param("type") String type, @Param("list") List<String> list);

    /**
     * 更新字典键值
     *
     * @param record
     * @return
     */

    int updateByObject(AccountDic record);




}