package com.jzfq.rms.account.enums;

import java.io.Serializable;

/**
 * 删除标识枚举
 */
public enum EnumDelFlag implements Serializable {
    DELETE_FLAG("0", "未删除"), UN_DELETE_FLAG("1", "已删除");

    private EnumDelFlag(String code, String message) {
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

    public static EnumDelFlag getEnum(String code) {
        for (EnumDelFlag statusEnum : EnumDelFlag.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
