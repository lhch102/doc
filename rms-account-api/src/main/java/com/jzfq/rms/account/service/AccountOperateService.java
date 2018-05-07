package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountOperate;
import com.jzfq.rms.account.bean.Extended.AccountOperateExtended;
import com.jzfq.rms.account.bean.Extended.AccountOperateRelationExtended;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;

import java.util.HashMap;
import java.util.List;

public interface AccountOperateService {


    /**
     * 分页查询操作权限数据
     *
     * @param menu
     * @return
     */

    PageData<AccountOperate> list(AccountMenuRequestModel menu) throws BusinessException;

    /**
     * 查询操作权限数据 不是分页
     *
     * @param menu
     * @return
     */

    List<AccountOperate> queryOperateByMenuNo(AccountMenuRequestModel menu) throws BusinessException;


    /**
     * 删除操作权限
     *
     * @param ids
     * @return
     */

    int del(List<String> ids);

    public List<AccountOperate> selectByMenuNo(HashMap<String, Object> paramNo);

    /**
     * 保存操作权限数据
     *
     * @param accountMenuRequestModel
     * @return
     */

    int save(AccountMenuRequestModel accountMenuRequestModel);

    public AccountOperateExtended selectExtendByMenuNo(HashMap<String, Object> paramNo);


    /**
     * 修改操作权限信息
     *
     * @param accountOperate
     * @return
     */

    int update(AccountOperate accountOperate);

    /**
     * 操作权限  启用/停用  方法
     *
     * @param paramMap
     */
    public void updateOperRelation(HashMap<String, Object> paramMap);

    void save(AccountOperateRelationExtended accountOperateRelationExtended);

    /**
     * 根据操作键id查询操作权限
     *
     * @param id
     * @return
     */

    AccountOperate queryByOperateId(String id);

}
