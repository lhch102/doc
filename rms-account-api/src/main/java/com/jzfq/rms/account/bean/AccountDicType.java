package com.jzfq.rms.account.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.account.web.requestModel.BaseRequestModel;

import java.util.Date;

public class AccountDicType extends BaseRequestModel {
    private Long id;

    private String typeName;

    private String type;

    private String remark;

    private String updateBy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


    private String enableFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }

}