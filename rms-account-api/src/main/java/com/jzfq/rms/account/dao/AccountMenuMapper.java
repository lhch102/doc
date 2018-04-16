package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountMenuMapper {
    int deleteByPrimaryKey(String menuNo);

    int insert(AccountMenu record);

    int insertSelective(AccountMenu record);

    AccountMenu selectByPrimaryKey(String menuNo);

    int updateByPrimaryKeySelective(AccountMenu record);

    int updateByPrimaryKey(AccountMenu record);

    /**
     * 获取最大菜单编号
     * @return
     */
    String getMaxMenuNo();

    /**
     * 根据系统编号查询菜单
     * @param menu
     * @return
     */
    List<AccountMenu> getMenuListBySystemNo(AccountMenu menu);



    /**
     * 获取菜单列表
     * @param menu
     * @return
     */
    List<AccountMenu> queryMenuList(AccountMenu menu);

    /**
     * 显示/隐藏
     *
     * @param type
     * @return
     */

    int showOrVisable(@Param("type") String type, @Param("list") List<String> list);

    /**
     * 校验菜单
     *
     * @param accountMenuRequestModel
     * @return
     */

    int checkMenu(AccountMenuRequestModel accountMenuRequestModel);


    public List<AccountMenu>queryRoleMenuList(AccountMenu menu);
}