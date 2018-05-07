package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.bean.AccountUserDeptKey;

import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 10:09
 */
public interface AccountUserDeptService {


    /**
     * 保存机构-用户信息
     * @param userIds,deptNo
     * @return
     */

    int save(List<String> userIds, String deptNo);


    /**
     * 修改机构-用户信息
     * @param userIds,deptNo
     * @return
     */

    int update(List<String> userIds,String deptNo);

    /**
     * 移除机构-用户信息
     * @param userId,deptNo
     * @return
     */

    int del(List<String> userId,String deptNo);


}
