package com.jzfq.rms.product.bean;

import java.util.List;

/**
 * 执行规则层级属性
 * @author 大连桔子分期科技有限公司
 * @date 2018/1/30 18:49
 */
public class RuleClassification {

    private String ruleNo;
    private String ruleClassicName;
    private List<RuleClassification> ruleClassiclist;

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleClassicName() {
        return ruleClassicName;
    }

    public void setRuleClassicName(String ruleClassicName) {
        this.ruleClassicName = ruleClassicName;
    }

    public List<RuleClassification> getRuleClassiclist() {
        return ruleClassiclist;
    }

    public void setRuleClassiclist(List<RuleClassification> ruleClassiclist) {
        this.ruleClassiclist = ruleClassiclist;
    }
}
