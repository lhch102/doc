package com.jzfq.rms.account.web.BeanValidators;

import com.jzfq.rms.account.bean.*;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.utils.DateUtils;
import com.jzfq.rms.account.web.requestModel.AccountDeptRequestModel;
import com.jzfq.rms.account.web.requestModel.AccountDicRequestModel;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import com.jzfq.rms.account.web.requestModel.DictionaryRequestModel;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018年4月08日 16:44:55
 */
public class BeanValidators {


    /**
     * 分页查询字典键值列表/根据字典类型查询字典信息 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestList(AccountDic model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getType())) {
                //字典类型字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_TYPE.code(), ReturnCode.ERROR_DIC_TYPE.msg(), null);
            }
        }
        return null;
    }

    /**
     * 分查询操作权限列表（根据菜单编号） 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestList(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getMenuNo())) {
                //菜单编号字段不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NO.code(), ReturnCode.ERROR_MENU_MENU_NO.msg(), null);
            }
        }
        return null;
    }

    /**
     * 启用/停用字典 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestIsUsing(DictionaryRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getType())) {
                //字典类型字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ISUSING_TYPE.code(), ReturnCode.ERROR_DIC_ISUSING_TYPE.msg(), null);
            } else if (null == model.getIds() || model.getIds().size() <= 0) {
                //字典id字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ID.code(), ReturnCode.ERROR_DIC_ID.msg(), null);
            }
        }
        return null;
    }

    /**
     * 移除用户 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestRemoveDebt(AccountDeptRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getDeptNo())) {
                //机构编号不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEBT_NO.code(), ReturnCode.ERROR_DEBT_DEBT_NO.msg(), null);
            } else if (null == model.getUserIds() || model.getUserIds().size() <= 0) {
                //用户编号不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEPT_USER_NOS.code(), ReturnCode.ERROR_DEBT_DEPT_USER_NOS.msg(), null);
            }
        }
        return null;
    }

    /**
     * 删除操作权限 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestDelOperate(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getMenuNo())) {
                //菜单编号不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NO.code(), ReturnCode.ERROR_MENU_MENU_NO.msg(), null);
            } else if (null == model.getIds() || model.getIds().size() <= 0) {
                //操作id集合不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_OPERATE_IDS.code(), ReturnCode.ERROR_MENU_OPERATE_IDS.msg(), null);
            }
        }
        return null;
    }

    /**
     * 启用/停用机构 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestDebtIsUsing(AccountDeptRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getType())) {
                //机构类型字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ISUSING_TYPE.code(), ReturnCode.ERROR_DIC_ISUSING_TYPE.msg(), null);
            } else if (null == model.getDeptNos() || model.getDeptNos().size() <= 0) {
                //机构编号字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEBT_NO.code(), ReturnCode.ERROR_DEBT_DEBT_NO.msg(), null);
            }
        }
        return null;
    }

    /**
     * 显示/隐藏菜单 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestDebtIsShowingMenu(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getType())) {
                //菜单类型字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ISUSING_TYPE.code(), ReturnCode.ERROR_DIC_ISUSING_TYPE.msg(), null);
            } else if (null == model.getMenuNos() || model.getMenuNos().size() <= 0) {
                //菜单编号字段不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NOS.code(), ReturnCode.ERROR_MENU_MENU_NOS.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验字典名称是否重复 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestCheckDic(DictionaryRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getTypeName())) {
                //字典名称字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_TYPE_NAME.code(), ReturnCode.ERROR_DIC_TYPE_NAME.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验菜单名称是否重复 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestCheckMenuName(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getMenuName())) {
                //菜单名称字段不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NAME.code(), ReturnCode.ERROR_MENU_MENU_NAME.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验菜单链接是否重复 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestCheckMenuHref(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getHref())) {
                //菜单链接字段不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_HREF.code(), ReturnCode.ERROR_MENU_MENU_HREF.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验菜单权限是否重复 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestCheckMenuPermission(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getPermission())) {
                //菜单链接字段不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_PERMISSION.code(), ReturnCode.ERROR_MENU_MENU_PERMISSION.msg(), null);
            }
        }
        return null;
    }


    /**
     * 校验字典id 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestId(DictionaryRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getId())) {
                //字典名称字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ID.code(), ReturnCode.ERROR_DIC_ID.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验菜单id 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestMenuId(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getMenuNo())) {
                //菜单编号不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NO.code(), ReturnCode.ERROR_MENU_MENU_NO.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验机构编码 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestDebtNo(AccountDeptRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getDeptNo())) {
                //机构编码字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEBT_NO.code(), ReturnCode.ERROR_DEBT_DEBT_NO.msg(), null);
            }
        }
        return null;
    }

    /**
     * 校验操作键id 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestOperateId(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getId())) {
                //操作键id不能为空
                return new ResponseResult(ReturnCode.ERROR_OPERATE_ID.code(), ReturnCode.ERROR_OPERATE_ID.msg(), null);
            }
        }
        return null;
    }



    /**
     * 保存字典 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestSave(DictionaryRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getTypeName())) {
                //字典名称字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_TYPE_NAME.code(), ReturnCode.ERROR_DIC_TYPE_NAME.msg(), null);
            } else if (StringUtils.isEmpty(model.getType())) {
                //字典类型字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_TYPE.code(), ReturnCode.ERROR_DIC_TYPE.msg(), null);
            } else if (StringUtils.isEmpty(model.getUpdateBy())) {
                //字典更新人字段不能为空
                return new ResponseResult(ReturnCode.ERROR_UPDATE_BY.code(), ReturnCode.ERROR_UPDATE_BY.msg(), null);
            } else if (StringUtils.isEmpty(model.getUpdateDate())) {
                //字典更新时间字段不能为空
                return new ResponseResult(ReturnCode.ERROR_UPDATE_DATE.code(), ReturnCode.ERROR_UPDATE_DATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getUpdateDate()) && null == DateUtils.stringToTsDate(model.getUpdateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (model.getDataKeyList() != null && model.getDataKeyList().size() > 0) {
                for (AccountDicRequestModel type : model.getDataKeyList()) {
                    if (null != isValidateRequestSaveDicKey(type)) {
                        return isValidateRequestSaveDicKey(type);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 保存机构 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestSaveDept(AccountDeptRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getDeptNo())) {
                //机构编码不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEBT_NO.code(), ReturnCode.ERROR_DEBT_DEBT_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getParentNo())) {
                //上级结构不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_PARENT_NO.code(), ReturnCode.ERROR_DEBT_PARENT_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getDeptName())) {
                //机构名称不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEPT_NAME.code(), ReturnCode.ERROR_DEBT_DEPT_NAME.msg(), null);
            } else if (StringUtils.isEmpty(model.getType())) {
                //机构类型不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEPT_TYPE.code(), ReturnCode.ERROR_DEBT_DEPT_TYPE.msg(), null);
            } else if (StringUtils.isEmpty(model.getSort())) {
                //排序值不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEPT_SORT.code(), ReturnCode.ERROR_DEBT_DEPT_SORT.msg(), null);
            } else if (StringUtils.isNotEmpty(model.getSort()) && !checkNumber(model.getSort())) {
                //校验排序值格式
                return new ResponseResult(ReturnCode.ERROR_SORT_FORMATE.code(), ReturnCode.ERROR_SORT_FORMATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getUpdateDate()) && null == DateUtils.stringToTsDate(model.getUpdateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getCreateDate()) && null == DateUtils.stringToTsDate(model.getCreateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (StringUtils.isEmpty(model.getPrimaryPersion())) {
                //主负责人不能为空
                return new ResponseResult(ReturnCode.ERROR_DEBT_DEPT_PRIMARYPERSION.code(), ReturnCode.ERROR_DEBT_DEPT_PRIMARYPERSION.msg(), null);
            }
        }
        return null;
    }


    /**
     * 保存菜单 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestSaveMenu(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getMenuNo())) {
                //菜单编码不能为空2
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NO.code(), ReturnCode.ERROR_MENU_MENU_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getSystemNo())) {
                //所属系统结构不能为空
                return new ResponseResult(ReturnCode.ERROR_SYSTEM_NO.code(), ReturnCode.ERROR_SYSTEM_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getParentNo())) {
                //上级菜单不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_PARENT_NO.code(), ReturnCode.ERROR_MENU_PARENT_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getMenuName())) {
                //菜单名称不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NAME.code(), ReturnCode.ERROR_MENU_MENU_NAME.msg(), null);
            } else if (StringUtils.isEmpty(model.getHref())) {
                //菜单链接不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_HREF.code(), ReturnCode.ERROR_MENU_MENU_HREF.msg(), null);
            } else if (StringUtils.isEmpty(model.getPermission())) {
                //菜单标识不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_PERMISSION.code(), ReturnCode.ERROR_MENU_MENU_PERMISSION.msg(), null);
            } else if (StringUtils.isEmpty(model.getSort())) {
                //排序值不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_SORT.code(), ReturnCode.ERROR_MENU_SORT.msg(), null);
            } else if (StringUtils.isNotEmpty(model.getSort()) && !checkNumber(model.getSort())) {
                //校验排序值格式
                return new ResponseResult(ReturnCode.ERROR_SORT_FORMATE.code(), ReturnCode.ERROR_SORT_FORMATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getUpdateDate()) && null == DateUtils.stringToTsDate(model.getUpdateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getCreateDate()) && null == DateUtils.stringToTsDate(model.getCreateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (null != model.getMenuOperateList() && model.getMenuOperateList().size() > 0) {
                //校验操作权限
                for (AccountOperate type : model.getMenuOperateList()) {
                    if (null != isValidateRequestSaveOperateMenu(type)) {
                        return isValidateRequestSaveOperateMenu(type);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 修改菜单 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestUpdateMenu(AccountMenuRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getMenuNo())) {
                //菜单编码不能为空2
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NO.code(), ReturnCode.ERROR_MENU_MENU_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getSystemNo())) {
                //所属系统结构不能为空
                return new ResponseResult(ReturnCode.ERROR_SYSTEM_NO.code(), ReturnCode.ERROR_SYSTEM_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getParentNo())) {
                //上级菜单不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_PARENT_NO.code(), ReturnCode.ERROR_MENU_PARENT_NO.msg(), null);
            } else if (StringUtils.isEmpty(model.getMenuName())) {
                //菜单名称不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_NAME.code(), ReturnCode.ERROR_MENU_MENU_NAME.msg(), null);
            } else if (StringUtils.isEmpty(model.getHref())) {
                //菜单链接不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_HREF.code(), ReturnCode.ERROR_MENU_MENU_HREF.msg(), null);
            } else if (StringUtils.isEmpty(model.getPermission())) {
                //菜单标识不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_MENU_PERMISSION.code(), ReturnCode.ERROR_MENU_MENU_PERMISSION.msg(), null);
            } else if (StringUtils.isEmpty(model.getSort())) {
                //排序值不能为空
                return new ResponseResult(ReturnCode.ERROR_MENU_SORT.code(), ReturnCode.ERROR_MENU_SORT.msg(), null);
            } else if (StringUtils.isNotEmpty(model.getSort()) && !checkNumber(model.getSort())) {
                //校验排序值格式
                return new ResponseResult(ReturnCode.ERROR_SORT_FORMATE.code(), ReturnCode.ERROR_SORT_FORMATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getUpdateDate()) && null == DateUtils.stringToTsDate(model.getUpdateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (!StringUtils.isEmpty(model.getCreateDate()) && null == DateUtils.stringToTsDate(model.getCreateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (null != model.getMenuOperateList() && model.getMenuOperateList().size() > 0) {
                //校验操作权限
                for (AccountOperate type : model.getMenuOperateList()) {
                    if (null != isValidateRequestSaveOperateMenu(type)) {
                        return isValidateRequestSaveOperateMenu(type);
                    }
                }
            }
        }
        return null;
    }


    /**
     * 编辑操作权限根据权限id 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestUpdateOperateMenu(AccountOperate model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getOperateKey())) {
                //操作名称不能为空
                return new ResponseResult(ReturnCode.ERROR_OPERATE_KEY.code(), ReturnCode.ERROR_OPERATE_KEY.msg(), null);
            } else if (StringUtils.isEmpty(model.getOperateValue())) {
                //操作值不能为空
                return new ResponseResult(ReturnCode.ERROR_OPERATE_KEY_VALUE.code(), ReturnCode.ERROR_OPERATE_KEY_VALUE.msg(), null);
            } else if (null == model.getId()) {
                //操作id不能为空
                return new ResponseResult(ReturnCode.ERROR_OPERATE_ID.code(), ReturnCode.ERROR_OPERATE_ID.msg(), null);
            }
        }
        return null;
    }

    /**
     * 添加操作权限 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestSaveOperateMenu(AccountOperate model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getOperateKey())) {
                //操作名称不能为空
                return new ResponseResult(ReturnCode.ERROR_OPERATE_KEY.code(), ReturnCode.ERROR_OPERATE_KEY.msg(), null);
            } else if (StringUtils.isEmpty(model.getOperateValue())) {
                //操作值不能为空
                return new ResponseResult(ReturnCode.ERROR_OPERATE_KEY_VALUE.code(), ReturnCode.ERROR_OPERATE_KEY_VALUE.msg(), null);
            }
        }
        return null;
    }


    /**
     * 编辑字典键值根据字典键id 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestSaveDicKey(AccountDicRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getLabel())) {
                //字典键字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_LABEL.code(), ReturnCode.ERROR_DIC_LABEL.msg(), null);
            } else if (StringUtils.isEmpty(model.getDicValue())) {
                //字典值不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_DICVALUE.code(), ReturnCode.ERROR_DIC_DICVALUE.msg(), null);
            } else if (null == model.getSort()) {
                //字典排序字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_SORT.code(), ReturnCode.ERROR_DIC_SORT.msg(), null);
            }
        }
        return null;
    }

    /**
     * 修改字典键值根据字典键id 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestUpdateDicKey(AccountDicRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getLabel())) {
                //字典键字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_LABEL.code(), ReturnCode.ERROR_DIC_LABEL.msg(), null);
            } else if (StringUtils.isEmpty(model.getDicValue())) {
                //字典值不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_DICVALUE.code(), ReturnCode.ERROR_DIC_DICVALUE.msg(), null);
            } else if (StringUtils.isEmpty(model.getSort())) {
                //字典排序字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_SORT.code(), ReturnCode.ERROR_DIC_SORT.msg(), null);
            } else if (StringUtils.isEmpty(model.getId())) {
                //字典排序字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ID.code(), ReturnCode.ERROR_DIC_ID.msg(), null);
            } else if (StringUtils.isNotEmpty(model.getSort()) && !checkNumber(model.getSort())) {
                //字典排序字段格式不正确
                return new ResponseResult(ReturnCode.ERROR_SORT_FORMATE.code(), ReturnCode.ERROR_SORT_FORMATE.msg(), null);
            }
        }
        return null;
    }


    /**
     * 修改字典 参数校验
     *
     * @param model
     * @return
     */

    public static final ResponseResult isValidateRequestUpdate(DictionaryRequestModel model) {
        if (null == model) {
            //参数不能为空
            return new ResponseResult(ReturnCode.ERROR_PARAMS_NOT_NULL.code(), ReturnCode.ERROR_PARAMS_NOT_NULL.msg(), null);
        } else {
            if (StringUtils.isEmpty(model.getTypeName())) {
                //字典名称字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_TYPE_NAME.code(), ReturnCode.ERROR_DIC_TYPE_NAME.msg(), null);
            } else if (StringUtils.isEmpty(model.getType())) {
                //字典类型字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_TYPE.code(), ReturnCode.ERROR_DIC_TYPE.msg(), null);
            } else if (StringUtils.isEmpty(model.getUpdateBy())) {
                //字典更新人字段不能为空
                return new ResponseResult(ReturnCode.ERROR_UPDATE_BY.code(), ReturnCode.ERROR_UPDATE_BY.msg(), null);
            } else if (StringUtils.isEmpty(model.getUpdateDate())) {
                //字典更新时间字段不能为空
                return new ResponseResult(ReturnCode.ERROR_UPDATE_DATE.code(), ReturnCode.ERROR_UPDATE_DATE.msg(), null);
            } else if (StringUtils.isEmpty(model.getId())) {
                //字典id字段不能为空
                return new ResponseResult(ReturnCode.ERROR_DIC_ID.code(), ReturnCode.ERROR_DIC_ID.msg(), null);
            } else if (!StringUtils.isEmpty(model.getUpdateDate()) && null == DateUtils.stringToTsDate(model.getUpdateDate())) {
                //日期格式校验失败
                return new ResponseResult(ReturnCode.ERROR_DATE_FORMATE.code(), ReturnCode.ERROR_DATE_FORMATE.msg(), null);
            } else if (model.getDataKeyList() != null && model.getDataKeyList().size() > 0) {
                for (AccountDicRequestModel type : model.getDataKeyList()) {
                    if (null != isValidateRequestSaveDicKey(type)) {
                        return isValidateRequestSaveDicKey(type);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 数字 格式校验
     *
     * @param str
     * @return
     */

    public static Boolean checkNumber(String str) {
        String regex = "[0-9]+([0-9]+)?";
        if (str == null || !str.matches(regex)) {
            return false;
        }
        return true;
    }


}
