package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.Extended.AccountOperateRelationExtended;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;

import java.util.HashMap;
import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 16:34
 */
public interface AccountMenuOperateService {

    /**
     * 删除菜单-操作权限
     *
     * @param ids,menuNo
     * @return
     */

    int del(List<String> ids, String menuNo);


    /**
     * 保存操作权限-菜单数据
     *
     * @param accountMenuOperateKey
     * @return
     */

    int save(AccountMenuOperateKey accountMenuOperateKey);


    /**
     * 保存 操作关系信息
     *
     * @param accountOperateRelationExtended
     */
    public void save(AccountOperateRelationExtended accountOperateRelationExtended);

    public void deleteByMenuNo(HashMap<String, Object> paramMap);

    int insert(AccountMenuOperateKey record);
}
