package com.jzfq.rms.product.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.product.bean.Extended.ConfigMobileExtended;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class ConfigMobile extends ConfigMobileExtended {

    private Integer id;

    /**
     * 策略编号
     */
    @NotBlank(message = "客群策略编号不能为空！")
    private String tacticskqId;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空！")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "手机号不符合规范！")
    private String mobile;

    /**
     * 截止有效期
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date validityPeriod;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTacticskqId() {
        return tacticskqId;
    }

    public void setTacticskqId(String tacticskqId) {
        this.tacticskqId = tacticskqId == null ? null : tacticskqId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getValidityPeriod() {
        return validityPeriod == null ? validityPeriod : (Date) validityPeriod.clone();
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod == null ? validityPeriod : (Date) validityPeriod.clone();

    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}