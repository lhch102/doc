package com.jzfq.rms.account.enums;

import java.io.Serializable;

public enum EnumLogin implements Serializable {
    UN_FINDNAME(0, "用户名不存在"), UN_FINDPWD(1, "密码失败"), VALIDATE_CODE_FALSE(2, "验证码失败"), SUCCESS(3, "登录成功"), FALED(4, "登录失败"),
    PASSWORD_EMPTY(5, "密码为空"),
    NAME_EMPTY(6, "用户名为空");

    private EnumLogin(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public String code() {
        return getCode().toString();
    }

    public String message() {
        return getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static EnumLogin getEnum(Integer code) {
        for (EnumLogin statusEnum : EnumLogin.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
