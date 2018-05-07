package com.jzfq.rms.account.web.requestModel;

import com.jzfq.rms.account.bean.AccountOperate;

import java.util.ArrayList;
import java.util.List;

public class AccountMenuRequestModel extends BaseRequestModel {


    private String id;//id

    private String menuNo;//菜单编号

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    private String type;//类型

    private List<String> menuNos = new ArrayList<String>(); //菜单编号集合

    private String permission;//菜单权限标识

    private String menuName;//菜单名称

    private String href;//菜单链接

    private String systemNo; //所属系统

    private String parentNo; //上级菜单

    private String sort; //排序值

    private String isShow;//是否显示

    private String createBy; //创建人

    private String remarks; //备注

    private String createDate; //创建时间

    private String delFlag; //删除标记

    private String updateBy; //更新人

    private String updateDate; //更新时间

    private String operateValue; //操作键

    private String operateKey;//操作键值

    private String operateRemark; //备注

    private List<AccountOperate> menuOperateList = new ArrayList<AccountOperate>(); //菜单操作权限

    private List<String> ids = new ArrayList<String>();//id集合 删除操作

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }


    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<String> getMenuNos() {
        return menuNos;
    }

    public void setMenuNos(List<String> menuNos) {
        this.menuNos = menuNos;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AccountOperate> getMenuOperateList() {
        return menuOperateList;
    }

    public void setMenuOperateList(List<AccountOperate> menuOperateList) {
        this.menuOperateList = menuOperateList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOperateValue() {
        return operateValue;
    }

    public void setOperateValue(String operateValue) {
        this.operateValue = operateValue;
    }

    public String getOperateKey() {
        return operateKey;
    }

    public void setOperateKey(String operateKey) {
        this.operateKey = operateKey;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }
}
