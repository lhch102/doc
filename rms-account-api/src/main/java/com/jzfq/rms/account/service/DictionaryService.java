package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.common.PageData;

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

    PageData<AccountDic> list(AccountDic dic);

    /**
     * 根据字典类型查询字典数据
     * @param type
     * @return
     */
    List<AccountDic> queryDicListByType(String type);

    /**
     * 启用/停用
     * @param type，dicid
     * @return
     */

    int isUsing(String type,String dicid);



}
