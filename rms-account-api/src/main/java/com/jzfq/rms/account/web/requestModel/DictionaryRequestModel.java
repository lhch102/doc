package com.jzfq.rms.account.web.requestModel;

import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.common.Page;

import java.util.ArrayList;
import java.util.List;

public class DictionaryRequestModel extends BaseRequestModel {

    private String description;//字典描述

    private String label;//字典键

    private String type;//字典类型

    private String dicId;//字典键值id

    private String typeName;//字典类型

    private String id;//字典类型id

    private String remark;//备注

    private String dicValue;//字典键值

    private String sort;//排序值

    private String delFlag;//状态

    private String updateBy;//更新人

    private String updateDate;//更新时间

    private List<AccountDicRequestModel> dataKeyList = new ArrayList<AccountDicRequestModel>(); //配置键值列表

    private List<String> ids = new ArrayList<String>();//字典id集合

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AccountDicRequestModel> getDataKeyList() {
        return dataKeyList;
    }

    public void setDataKeyList(List<AccountDicRequestModel> dataKeyList) {
        this.dataKeyList = dataKeyList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
