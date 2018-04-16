package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.web.requestModel.AccountDeptRequestModel;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;

import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 16:34
 */
public interface AccountMenuService {

    /**
     * 根据系统编号查询菜单
     * @param systemNo
     * @return
     */
    AccountMenu getMenuListBySystemNo(String systemNo)throws BusinessException;

    /**
     * 获取菜单列表
     * @param menu
     * @return
     */
    List<AccountMenu> queryMenuList(AccountMenu menu)throws BusinessException;

    /**
     * 显示/隐藏
     * @param type，menuNos
     * @return
     */

    int showOrVisable(String type,List<String> menuNos)throws BusinessException;

    /**
     * 校验菜单名称/链接/标识是否存在
     * @param accountMenuRequestModel
     * @return
     */

    boolean checkMenu(AccountMenuRequestModel accountMenuRequestModel);

    /**
     * 根据菜单编号查询菜单信息
     * @param menuNo
     * @return
     */

    AccountMenu queryMenuById(String menuNo);

    /**
     * 保存菜单数据
     * @param model
     * @return
     */

    int save(AccountMenuRequestModel model);

    /**
     * 修改菜单数据
     * @param accountMenuRequestModel
     * @return
     */

    int update(AccountMenuRequestModel accountMenuRequestModel);



    public List<AccountMenu>queryRoleMenuList(AccountMenu menu);


}
