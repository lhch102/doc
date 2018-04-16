package com.jzfq.rms.account.enums;

/**
 * 是否显示枚举
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 18:05
 */
public enum EnumIsShow {

    SHOW("0", "显示"),
    HIDE("1", "隐藏");

    EnumIsShow(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

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

    public static EnumIsShow getEnum(String code) {
        for (EnumIsShow statusEnum : EnumIsShow.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
