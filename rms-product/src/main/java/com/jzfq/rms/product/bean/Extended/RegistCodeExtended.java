package com.jzfq.rms.product.bean.Extended;

import com.jzfq.rms.product.common.Page;

import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/17 17:34
 */
public class RegistCodeExtended extends Page {

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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
