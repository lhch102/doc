package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.bean.AccountDept;
import com.jzfq.rms.account.common.Page;

import java.util.List;

/**
 * 机构实体扩展类
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 11:17
 */
public class AccountDeptExtended extends Page {


    /**
     * 启用标识名称
     */
    private String enableFlagName;

    /**
     * 机构类型名称
     */
    private String typeName;

    /**
     * 机构子集
     */
    List<AccountDept> childDept;

    public List<AccountDept> getChildDept() {
        return childDept;
    }

    public void setChildDept(List<AccountDept> childDept) {
        this.childDept = childDept;
    }

    public String getEnableFlagName() {
        return enableFlagName;
    }

    public void setEnableFlagName(String enableFlagName) {
        this.enableFlagName = enableFlagName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
