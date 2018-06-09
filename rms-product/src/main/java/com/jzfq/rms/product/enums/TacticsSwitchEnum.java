package com.jzfq.rms.product.enums;

/**
 * 自动通过开关枚举
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/12 16:27
 */
public enum TacticsSwitchEnum {

    ENABLED("0","开启"),
    DISABLED("1","关闭");

    private String code;
    private String name;


    TacticsSwitchEnum(String code, String name) {
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
