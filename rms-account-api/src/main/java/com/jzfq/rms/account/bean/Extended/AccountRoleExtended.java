package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.common.Page;

/**
 * 角色扩展类
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 17:31
 */
public class AccountRoleExtended extends Page{

    /**
     * 职位名称
     */
    private String positionTypeName;
    /**
     * 启用标识名称
     */
    private String enableFlagName;
    /**
     * 所属系统名称
     */
    private String systemName;
    /**
     * 机构类型名称
     */
    private String typeName;
    /**
     * 序号
     */
    private int No_;

    public String getPositionTypeName() {
        return positionTypeName;
    }

    public void setPositionTypeName(String positionTypeName) {
        this.positionTypeName = positionTypeName;
    }

    public String getEnableFlagName() {
        return enableFlagName;
    }

    public void setEnableFlagName(String enableFlagName) {
        this.enableFlagName = enableFlagName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getNo_() {
        return No_;
    }

    public void setNo_(int no_) {
        No_ = no_;
    }
}
