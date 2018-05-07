package com.jzfq.rms.account.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzfq.rms.account.bean.Extended.AccountMenuExtended;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单实体
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 11:17
 */
public class AccountMenu extends AccountMenuExtended {
    private String menuNo;

    private String parentNo;

    private String parentNos;

    private String menuName;

    private Long sort;

    private String href;

    private String target;

    private String icon;

    private String isShow;

    private String permission;

    private String createBy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String updateBy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String systemNo;

    private List<AccountMenu> childrens = new ArrayList<AccountMenu>();

    private List<AccountOperate> menuOperateList = new ArrayList<AccountOperate>(); //菜单操作权限

    public List<AccountOperate> getMenuOperateList() {
        return menuOperateList;
    }

    public void setMenuOperateList(List<AccountOperate> menuOperateList) {
        this.menuOperateList = menuOperateList;
    }

    public List<AccountMenu> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<AccountMenu> childrenList) {
        this.childrenList = childrenList;
    }

    private List<AccountMenu>childrenList;

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo == null ? null : menuNo.trim();
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo == null ? null : parentNo.trim();
    }

    public String getParentNos() {
        return parentNos;
    }

    public void setParentNos(String parentNos) {
        this.parentNos = parentNos == null ? null : parentNos.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo == null ? null : systemNo.trim();
    }

    public List<AccountMenu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<AccountMenu> childrens) {
        this.childrens = childrens;
    }
}