package com.jzfq.rms.account.enums;


/**
 * 操作类型枚举类型
 */

public enum EnumOperateType {

    BUTTON_PERMISSIONS("0", "按钮权限"), SELECT_PERMISSIONS("1", "查询权限"), DEPT_PERMISSIONS("2", "机构权限");

    private EnumOperateType(String code, String message) {
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

    public static EnumOperateType getEnum(String code) {
        for (EnumOperateType statusEnum : EnumOperateType.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }


}
