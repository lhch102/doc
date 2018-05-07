package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountDept;
import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.web.requestModel.AccountDeptRequestModel;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 10:09
 */
public interface AccountDeptService {

    /**
     * 获取所属机构
     *
     * @param debt
     * @return
     */
    List<AccountDept> getDeptList(AccountDept debt) throws BusinessException;

    /**
     * 启用/停用
     *
     * @param type，debtNo
     * @return
     */

    int isUsing(String type, List<String> debtNo) throws BusinessException;

    /**
     * 根据机构编码查询自信息
     *
     * @param deptNo
     * @return
     */

    AccountDept queryModel(String deptNo);

    /**
     * 保存机构数据
     *
     * @param accountDept
     * @return
     */

    int save(AccountDeptRequestModel accountDept);

    /**
     * 修改机构数据
     *
     * @param accountDept
     * @return
     */

    int update(AccountDeptRequestModel accountDept);

    /**
     * 根据用户编号查询用户所属机构
     *
     * @param userNo
     * @return
     */
    List<Map<String, Object>> getDeptListByUserNo(String userNo);
}
