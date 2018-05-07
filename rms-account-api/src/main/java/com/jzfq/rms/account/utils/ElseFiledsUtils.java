package com.jzfq.rms.account.utils;

import com.jzfq.rms.account.enums.EnumModelType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018年4月04日 20:04:55
 */
public class ElseFiledsUtils {

    /**
     * 根据不同的业务 去除不需要覆盖的字段
     *
     * @param type
     * @return
     */

    public static List<String> elseFileds(String type) {
        List<String> elseFileds = new ArrayList<String>();
        if (EnumModelType.MODEL_DIC.code().equals(type)) {
            //字典
            elseFileds.add("type");
            elseFileds.add("description");
            elseFileds.add("remark");
            elseFileds.add("del_flag");
        } else if (EnumModelType.MODEL_DEPT.code().equals(type)) {
            //机构
            elseFileds.add("parentNos");
            elseFileds.add("grade");
            elseFileds.add("del_flag");
        }else if (EnumModelType.MODEL_MENU.code().equals(type)) {
            //菜单
            elseFileds.add("target");
            elseFileds.add("icon");
            elseFileds.add("del_flag");
        }else if (EnumModelType.MODEL_MENU.code().equals(type)) {
            //操作权限
            elseFileds.add("id");
            elseFileds.add("operate_type");
            elseFileds.add("enable");
        }else if (EnumModelType.MODEL_DIC_TYPE.code().equals(type)) {
            //字典类型
            elseFileds.add("enableFlag");
        }
        return elseFileds;
    }


}
