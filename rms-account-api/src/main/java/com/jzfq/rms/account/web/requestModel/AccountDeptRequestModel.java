package com.jzfq.rms.account.web.requestModel;

import java.util.ArrayList;
import java.util.List;

public class AccountDeptRequestModel extends BaseRequestModel {

    private String deptNo;//机构编码

    private String parentNo;//上级机构

    private String deptName;//机构名称

    private String sort; //排序值

    private String type; //机构类型

    private String primaryPersion; //主负责人

    private String deputyPersion;//	副负责人

    private String createBy; //创建人

    private String createDate; // 创建时间

    private String updateBy; //更新人

    private String updateDate; //更新时间

    private String grade; //机构登记

    private String remarks;//remarks

    private String enableFlag;//机构状态

    private String delFlag; //删除标识

    private List<String> userIds = new ArrayList<String>();//配置用户ids

    private List<String> deptNos = new ArrayList<String>(); //机构集合id


    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }


    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryPersion() {
        return primaryPersion;
    }

    public void setPrimaryPersion(String primaryPersion) {
        this.primaryPersion = primaryPersion;
    }

    public String getDeputyPersion() {
        return deputyPersion;
    }

    public void setDeputyPersion(String deputyPersion) {
        this.deputyPersion = deputyPersion;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getDeptNos() {
        return deptNos;
    }

    public void setDeptNos(List<String> deptNos) {
        this.deptNos = deptNos;
    }
}
