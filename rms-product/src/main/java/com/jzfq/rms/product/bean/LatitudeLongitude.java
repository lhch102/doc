package com.jzfq.rms.product.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.product.bean.Extended.LatitudeLongitudeExtended;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

public class LatitudeLongitude extends LatitudeLongitudeExtended {
    private Integer id;

    /**
     * 策略编号
     */
    @NotBlank(message = "客群策略编号不能为空！")
    private String tacticskqId;

    /**
     * 纬度最小值
     */
    @NotBlank(message = "纬度不能为空！")
    @Pattern(regexp = "^(\\-|\\+)?([0-8]?\\d{1}\\.\\d{0,6}|90\\.0{0,6}|[0-8]?\\d{1}|90)$", message = "纬度不符合规范！")
    private BigDecimal latMin;

    /**
     * 纬度最大值
     */
    @NotBlank(message = "纬度不能为空！")
    @Pattern(regexp = "^(\\-|\\+)?([0-8]?\\d{1}\\.\\d{0,6}|90\\.0{0,6}|[0-8]?\\d{1}|90)$", message = "纬度不符合规范！")
    private BigDecimal latMax;

    /**
     * 经度最小值
     */
    @NotBlank(message = "经度不能为空！")
    @Pattern(regexp = "^(\\-|\\+)?(((\\d|[1-9]\\d|1[0-7]\\d|0{1,3})\\.\\d{0,6})|(\\d|[1-9]\\d|1[0-7]\\d|0{1,3})|180\\.0{0,6}|180)$", message = "经度不符合规范！")
    private BigDecimal lngMin;

    /**
     * 经度最大值
     */
    @NotBlank(message = "经度不能为空！")
    @Pattern(regexp = "^(\\-|\\+)?(((\\d|[1-9]\\d|1[0-7]\\d|0{1,3})\\.\\d{0,6})|(\\d|[1-9]\\d|1[0-7]\\d|0{1,3})|180\\.0{0,6}|180)$", message = "经度不符合规范！")
    private BigDecimal lngMax;

    /**
     * 有效截止期
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

    public BigDecimal getLatMin() {
        return latMin;
    }

    public void setLatMin(BigDecimal latMin) {
        this.latMin = latMin;
    }

    public BigDecimal getLatMax() {
        return latMax;
    }

    public void setLatMax(BigDecimal latMax) {
        this.latMax = latMax;
    }

    public BigDecimal getLngMin() {
        return lngMin;
    }

    public void setLngMin(BigDecimal lngMin) {
        this.lngMin = lngMin;
    }

    public BigDecimal getLngMax() {
        return lngMax;
    }

    public void setLngMax(BigDecimal lngMax) {
        this.lngMax = lngMax;
    }

    public Date getValidityPeriod() {
        return validityPeriod == null ? validityPeriod : (Date)validityPeriod.clone();
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod == null ? validityPeriod : (Date)validityPeriod.clone();

    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}