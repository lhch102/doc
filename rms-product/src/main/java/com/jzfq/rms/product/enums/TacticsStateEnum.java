package com.jzfq.rms.product.enums;

/**
 * 策略状态枚举
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/1/23 19:00
 */
public enum TacticsStateEnum {

    ENABLED("0","启用"),
    DISABLED("1","停用");

    private String code;
    private String name;

    TacticsStateEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public static String getName(String code){
        for(TacticsStateEnum c: TacticsStateEnum.values()){
            if(c.getCode().equals(code)){
                return c.getName();
            }
        }
        return null;
    }
}
