package com.jzfq.rms.account.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.account.bean.Extended.AccountUserExtended;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 用户表实体类
 * @author 大连桔子分期科技有限公司
 * @date 2018年04月02日
 */
public class AccountUser extends AccountUserExtended {

    /**
     * 用户业务编号
     */
    @NotBlank(message = "用户编号不能为空！")
    private String userNo;
    /**
     * 登录名
     */
    @NotBlank(message = "登录名不能为空！")
    private String loginName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空！")
    private String password;
    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空！")
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    @NotBlank(message = "手机号不能为空！")
    private String mobile;
    /**
     * 用户类型,1=超级管理员，0=普通用户
     */
    @NotBlank(message = "用户类型不能为空！")
    private String userType;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 有效期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date period;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**
     * 职位类型,字典表
     */
    @NotBlank(message = "职位类型不能为空！")
    private String positionType;
    /**
     * 用户头像
     */
    private String photo;
    /**
     * 最后登陆IP
     */
    private String loginIp;
    /**
     * 最后登陆时间
     */
    private Date loginDate;
    /**
     * 启用标识，0=启用，1=停用
     */
    @NotBlank(message = "状态不能为空！")
    private String enableFlag;
    /**
     * 删除标识；0：未删除；1：已删除；
     */
    private String delFlag;



    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
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

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType == null ? null : positionType.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
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