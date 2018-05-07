package com.jzfq.rms.account.web.responseModel;

public class InitResponseModel {


    private String createBy;

    private String debtNo;


    public InitResponseModel() {

    }

    public InitResponseModel(String createBy, String debtNo) {
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
