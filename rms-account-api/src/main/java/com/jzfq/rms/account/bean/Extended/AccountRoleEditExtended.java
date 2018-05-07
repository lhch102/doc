package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.bean.*;

import java.util.List;

/**
 * 角色编辑/新增 页面，封装实体
 */
public class AccountRoleEditExtended {
    /**
     * 菜单
     */

    private List<AccountMenu> accountMenus;
    /**
     * 角色详细
     */

    private AccountRole accountRole;
    /**
     * 系统下拉框
     */
    private List<AccountSystem> accountSystem;
    /**
     * 状态下拉框
     */
    private List<AccountDic> accountDic;

    public List<AccountMenu> getAccountMenus() {
        return accountMenus;
    }

    public void setAccountMenus(List<AccountMenu> accountMenus) {
        this.accountMenus = accountMenus;
    }

    public AccountRole getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
    }

    public List<AccountSystem> getAccountSystem() {
        return accountSystem;
    }

    public void setAccountSystem(List<AccountSystem> accountSystem) {
        this.accountSystem = accountSystem;
    }

    public List<AccountDic> getAccountDic() {
        return accountDic;
    }

    public void setAccountDic(List<AccountDic> accountDic) {
        this.accountDic = accountDic;
    }


}
