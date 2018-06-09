package com.jzfq.rms.product.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.product.bean.Extended.CustomerTypeExtended;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 客群策略实体
 */
public class CustomerType extends CustomerTypeExtended {

    private Integer customerTypeId;

    /**
     * 策略编号
     */
    @NotBlank(message = "策略编号不能为空！")
    private String tacticskqId;

    /**
     * 客群名称
     */
    @NotBlank(message = "客群名称不能为空！")
    private String customerType;

    /**
     * 渠道ID
     */
    @NotBlank(message = "渠道ID不能为空！")
    private String channelId;


    /**
     * 状态，0:启用; 1:停用;
     */
    @NotBlank(message = "状态不能为空！")
    private String customerStatus;

    /**
     * 优先级
     */
    @NotNull(message = "优先级不能为空！")
    private Integer customerLevel;

    /**
     * 删除标识（0：可用、1：已删除）
     */
    private String deleteFlag;

    /**
     * 备注
     */
    private String remark;

    private String createUser;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getTacticskqId() {
        return tacticskqId;
    }

    public void setTacticskqId(String tacticskqId) {
        this.tacticskqId = tacticskqId == null ? null : tacticskqId.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus == null ? null : customerStatus.trim();
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime == null ? createTime : (Date) createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? createTime : (Date) createTime.clone();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime == null ? updateTime : (Date) updateTime.clone();
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime == null ? updateTime : (Date) updateTime.clone();
    }

}