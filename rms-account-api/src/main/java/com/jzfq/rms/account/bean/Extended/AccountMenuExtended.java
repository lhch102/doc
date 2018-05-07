package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.common.Page;

import java.util.List;

/**
 * 菜单实体扩展类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 17:33
 */
public class AccountMenuExtended extends Page {

    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 菜单子集
     */
    List<AccountMenu> childMenu;

    /**
     * 菜单是否已分配给角色， 0=未分配，1=已分配
     */
    private String allot;

    private String roleNo;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getAllot() {
        return allot;
    }

    public void setAllot(String allot) {
        this.allot = allot;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<AccountMenu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<AccountMenu> childMenu) {
        this.childMenu = childMenu;
    }
}
