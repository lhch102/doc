package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.Extended.AccountOperateExtended;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AccountMenuOperateMapper {

    int deleteByPrimaryKey(AccountMenuOperateKey key);

    int insert(AccountMenuOperateKey record);

    int insertSelective(AccountMenuOperateKey record);


    /**
     * 获取旧的菜单-操作权限关联列表
     *
     * @param menuNo
     * @return
     */

    List<String> queryOldMenuOperateList(@Param("menuNo") String menuNo);



    public void deleteByMenuNo(HashMap<String,Object> hashMap);
}