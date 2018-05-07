package com.jzfq.rms.account.bean;

import org.hibernate.validator.constraints.NotBlank;

public class AccountUserDeptKey {

    @NotBlank(message = "用户编号不能为空！")
    private String userNo;
    @NotBlank(message = "机构编号不能为空！")
    private String deptNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }
}