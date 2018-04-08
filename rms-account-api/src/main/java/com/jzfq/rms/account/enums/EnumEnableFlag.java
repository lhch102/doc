package com.jzfq.rms.account.enums;

import java.io.Serializable;

/**
 * 启用标识枚举
 */
public enum EnumEnableFlag implements Serializable {
    ENABLE("0", "启用"), UN_ENABLE("1", "停用");

    private EnumEnableFlag(String code, String message) {
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

    public static EnumEnableFlag getEnum(String code) {
        for (EnumEnableFlag statusEnum : EnumEnableFlag.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
