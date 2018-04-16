package com.jzfq.rms.account.bean.Extended;

public class AccountOperateParamExtended {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    private String operateValue;
    private String operateKey;
    private String operateRemark;
    private String operateType;//0=按钮权限，1=查询权限，2=部门权限

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateValue() {
        return operateValue;
    }

    public void setOperateValue(String operateValue) {
        this.operateValue = operateValue;
    }

    public String getOperateKey() {
        return operateKey;
    }

    public void setOperateKey(String operateKey) {
        this.operateKey = operateKey;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }


}
