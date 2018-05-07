package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.bean.AccountOperate;

import java.util.ArrayList;
import java.util.List;

public class AccountOperateExtended {
    public List<AccountOperate> getButtonOperate() {
        return buttonOperate;
    }

    public void setButtonOperate(List<AccountOperate> buttonOperate) {
        this.buttonOperate = buttonOperate;
    }

    public List<AccountOperate> getSelectOperate() {
        return selectOperate;
    }

    public void setSelectOperate(List<AccountOperate> selectOperate) {
        this.selectOperate = selectOperate;
    }

    public List<AccountOperate> getDeptOperate() {
        return deptOperate;
    }

    public void setDeptOperate(List<AccountOperate> deptOperate) {
        this.deptOperate = deptOperate;
    }

    /**
     * 操作权限
     */
    private List<AccountOperate> buttonOperate = new ArrayList<AccountOperate>();
    /**
     * 查询权限
     */
    private List<AccountOperate> selectOperate = new ArrayList<AccountOperate>();
    /**
     * 机构权限
     */
    private List<AccountOperate> deptOperate = new ArrayList<AccountOperate>();


}
