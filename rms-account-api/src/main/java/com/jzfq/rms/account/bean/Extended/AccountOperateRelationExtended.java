package com.jzfq.rms.account.bean.Extended;

import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.AccountOperate;

import java.util.ArrayList;
import java.util.List;

public class AccountOperateRelationExtended {
    private String menuNo;
    private AccountOperateParamExtended operate;

    public AccountOperateParamExtended getOperate() {
        return operate;
    }

    public void setOperate(AccountOperateParamExtended operate) {
        this.operate = operate;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }
}
