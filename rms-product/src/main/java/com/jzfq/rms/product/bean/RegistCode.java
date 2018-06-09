package com.jzfq.rms.product.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.product.bean.Extended.RegistCodeExtended;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class RegistCode extends RegistCodeExtended {
    private Integer id;

    /**
     * 策略编号
     */
    @NotBlank(message = "客群策略编号不能为空！")
    private String tacticskqId;

    /**
     * 注册推荐码
     */
    @NotBlank(message = "注册推荐码不能为空！")
    private String registCode;

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

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode == null ? null : registCode.trim();
    }

    public Date getValidityPeriod() {
        return validityPeriod == null ? validityPeriod :(Date)validityPeriod.clone();
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod == null ? validityPeriod :(Date)validityPeriod.clone();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}