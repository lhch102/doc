package com.jzfq.rms.account.bean;

import com.jzfq.rms.account.web.requestModel.BaseRequestModel;

public class AccountOperate extends BaseRequestModel{
    private Long id;

    private String operateValue;

    private String operateKey;

    private String operateRemark;

    private String operateType;

    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateValue() {
        return operateValue;
    }

    public void setOperateValue(String operateValue) {
        this.operateValue = operateValue == null ? null : operateValue.trim();
    }

    public String getOperateKey() {
        return operateKey;
    }

    public void setOperateKey(String operateKey) {
        this.operateKey = operateKey == null ? null : operateKey.trim();
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark == null ? null : operateRemark.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }
}