package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.web.requestModel.AccountDicRequestModel;

import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:44
 */
public interface DictionaryService {


    /**
     * 分页查询字典数据
     * @param dic
     * @return
     */

    PageData<AccountDic> list(AccountDic dic)throws BusinessException;

    /**
     * 根据字典类型查询字典数据
     * @param type
     * @return
     */
    List<AccountDic> queryDicListByType(String type)throws BusinessException;

    /**
     * 启用/停用
     * @param type，dicid
     * @return
     */

    int isUsing(String type,List<String> dicid)throws BusinessException;

    /**
     * 保存字典数据
     * @param dicList,type
     * @return
     */

    int save(List<AccountDicRequestModel> dicList, String type);

    /**
     * 修改字典数据
     * @param dic
     * @return
     */

    int update(AccountDicRequestModel dic);

    /**
     * 根据字典id查询字典数据
     * @param id
     * @return
     */

    AccountDic queryDicKeyById(String id);



}
