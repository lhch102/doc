package com.jzfq.rms.account.web.responseModel;


/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:43
 * description:初始化字典接口返回model
 */
public class DicInitResponseModel {


    private String createBy;

    private String debtNo;


    public DicInitResponseModel() {

    }

    public DicInitResponseModel(String createBy, String debtNo) {
        this.createBy = createBy;
        this.debtNo = debtNo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDebtNo() {
        return debtNo;
    }

    public void setDebtNo(String debtNo) {
        this.debtNo = debtNo;
    }

    @Override
    public String toString() {
        return "DicInitResponseModel{" +
                "createBy='" + createBy + '\'' +
                ", debtNo='" + debtNo + '\'' +
                '}';
    }
}
