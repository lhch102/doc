package com.jzfq.rms.product.bean.Extended;

import com.jzfq.rms.product.common.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/17 17:40
 */
public class LatitudeLongitudeExtended extends Page {

    /**
     * id集合
     */
    private List<Integer> ids;
    /**
     * 开始有效期
     */
    private String startValidityPeriod;
    /**
     * 结束有效期
     */
    private String endValidityPeriod;

    /**
     * 经度和纬度
     */
    private BigDecimal latAndlng;

    /**
     * 经度范围
     */
    private String lngRange;

    /**
     * 纬度范围
     */
    private String latRange;

    public String getStartValidityPeriod() {
        return startValidityPeriod;
    }

    public void setStartValidityPeriod(String startValidityPeriod) {
        this.startValidityPeriod = startValidityPeriod;
    }

    public String getEndValidityPeriod() {
        return endValidityPeriod;
    }

    public void setEndValidityPeriod(String endValidityPeriod) {
        this.endValidityPeriod = endValidityPeriod;
    }

    public BigDecimal getLatAndlng() {
        return latAndlng;
    }

    public void setLatAndlng(BigDecimal latAndlng) {
        this.latAndlng = latAndlng;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getLngRange() {
        return lngRange;
    }

    public void setLngRange(String lngRange) {
        this.lngRange = lngRange;
    }

    public String getLatRange() {
        return latRange;
    }

    public void setLatRange(String latRange) {
        this.latRange = latRange;
    }
}
