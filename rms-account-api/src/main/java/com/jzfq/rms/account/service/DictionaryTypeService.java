package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.web.requestModel.DictionaryRequestModel;

import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:44
 */
public interface DictionaryTypeService {


    /**
     * 分页查询字典数据
     * @param dic
     * @return
     */

    PageData<AccountDicType> list(AccountDicType dic);



    /**
     * 校验字典名称是否存在
     * @param typeName,id
     * @return
     */

    boolean checkTypeName(String typeName,String id);


    /**
     * 根据字典id查询自信息
     * @param id
     * @return
     */

    AccountDicType queryModel(String id);

    /**
     * 保存字典信息
     * @param dictionaryRequestModel
     * @return
     */

    int save(DictionaryRequestModel dictionaryRequestModel);

    /**
     * 修改字典信息
     * @param dictionaryRequestModel
     * @return
     */

    int update(DictionaryRequestModel dictionaryRequestModel);

}
