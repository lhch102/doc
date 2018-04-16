package com.jzfq.rms.account.enums;

/**
 * 业务枚举类型
 */
public enum EnumModelType {


    MODEL_DEPT("dept", "机构"), MODEL_MENU("menu", "菜单"), MODEL_OPERATE("operate", "操作权限"), MODEL_DIC("dic", "字典"),MODEL_DIC_TYPE("dicType", "字典");

    private EnumModelType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String code() {
        return getCode().toString();
    }

    public String message() {
        return getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static EnumModelType getEnum(String code) {
        for (EnumModelType statusEnum : EnumModelType.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }


}
