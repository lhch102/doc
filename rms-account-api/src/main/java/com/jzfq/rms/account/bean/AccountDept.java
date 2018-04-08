package com.jzfq.rms.account.bean;

import java.util.Date;

public class AccountDept extends BaseBean{
    private String deptNo;

    private String parentNo;

    private String parentNos;

    private String deptName;

    private Long sort;

    private String type;

    private String primaryPersion;

    private String deputyPersion;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String grade;

    private String remarks;

    private String enableFlag;

    private String delFlag;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo == null ? null : parentNo.trim();
    }

    public String getParentNos() {
        return parentNos;
    }

    public void setParentNos(String parentNos) {
        this.parentNos = parentNos == null ? null : parentNos.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPrimaryPersion() {
        return primaryPersion;
    }

    public void setPrimaryPersion(String primaryPersion) {
        this.primaryPersion = primaryPersion == null ? null : primaryPersion.trim();
    }

    public String getDeputyPersion() {
        return deputyPersion;
    }

    public void setDeputyPersion(String deputyPersion) {
        this.deputyPersion = deputyPersion == null ? null : deputyPersion.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}