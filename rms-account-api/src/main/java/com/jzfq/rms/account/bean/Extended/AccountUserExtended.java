package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.bean.AccountDept;
import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountRole;
import com.jzfq.rms.account.bean.BaseBean;
import com.jzfq.rms.account.common.Page;
import com.jzfq.rms.account.common.PageData;

import java.util.List;
import java.util.Map;

/**
 * 用户表实体扩展类
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/2 18:30
 */
public class AccountUserExtended extends Page{

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
     * 所属机构列表
     */
    List<Map<String,Object>> belongsToDeptList;

    /**
     * 所属角色列表
     */
    PageData<AccountRole> belongsToRoleList;

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

    public List<Map<String, Object>> getBelongsToDeptList() {
        return belongsToDeptList;
    }

    public void setBelongsToDeptList(List<Map<String, Object>> belongsToDeptList) {
        this.belongsToDeptList = belongsToDeptList;
    }

    public PageData<AccountRole> getBelongsToRoleList() {
        return belongsToRoleList;
    }

    public void setBelongsToRoleList(PageData<AccountRole> belongsToRoleList) {
        this.belongsToRoleList = belongsToRoleList;
    }
}
