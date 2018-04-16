package com.jzfq.rms.account.bean;

import org.hibernate.validator.constraints.NotBlank;

public class AccountUserRoleKey {

    @NotBlank(message = "用户编号不能为空！")
    private String userNo;
    @NotBlank(message = "角色编号不能为空！")
    private String roleNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo == null ? null : roleNo.trim();
    }
}