package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDept;
import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.dao.AccountMenuMapper;
import com.jzfq.rms.account.enums.EnumDelFlag;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.EnumIsShow;
import com.jzfq.rms.account.enums.EnumModelType;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountMenuService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.utils.ElseFiledsUtils;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 16:38
 */
@Service
public class AccountMenuServiceImpl implements AccountMenuService {

    private final static Logger logger = LoggerFactory.getLogger(AccountMenuServiceImpl.class);


    @Autowired
    private AccountMenuMapper menuMapper;
    @Autowired
    LoginCommon loginCommon;

    /**
     * 根据系统编号查询菜单
     *
     * @param systemNo
     * @return
     */
    @Override
    public AccountMenu getMenuListBySystemNo(String systemNo) throws BusinessException {
        AccountMenu menu = new AccountMenu();
        menu.setSystemNo(systemNo);
        menu.setDelFlag(EnumDelFlag.DELETE_FLAG.getCode());
        menu.setIsShow(EnumIsShow.SHOW.getCode());
        List<AccountMenu> menuList = menuMapper.getMenuListBySystemNo(menu);
        return treeDeptList(menuList);
    }

    /**
     * 获取父节点下面的所有子节点
     *
     * @param menuList
     * @return
     */
    public AccountMenu treeDeptList(List<AccountMenu> menuList) {
        List<AccountMenu> nodeList = new ArrayList<>();
        if (menuList != null && menuList.size() > 0) {
            for (AccountMenu menu1 : menuList) {
                boolean mark = false;
                for (AccountMenu menu2 : menuList) {
                    if (menu1.getParentNo() != null && !"".equals(menu1.getParentNo())) {
                        if (menu1.getParentNo().equals(menu2.getMenuNo())) {
                            mark = true;
                            if (menu2.getChildMenu() == null) {
                                menu2.setChildMenu(new ArrayList<>());
                            }
                            menu2.getChildMenu().add(menu1);
                            break;
                        }
                    }
                }
                if (!mark) {
                    nodeList.add(menu1);
                }
            }
        }
        return nodeList.get(0);
    }

    /**
     * 获取菜单列表
     *
     * @param menu
     * @return
     */

    @Override
    public List<AccountMenu> queryMenuList(AccountMenu menu) throws BusinessException {
        logger.info("开始连接数据库获取菜单列表数据，参数信息：" + JSONObject.toJSONString(menu));
        List<AccountMenu> menuList = menuMapper.queryMenuList(menu);
        if (null != menuList && menuList.size() > 0) {
            logger.info("连接数据库获取菜单数据成功，返回数据：" + JSONObject.toJSONString(menuList));
            return menusTree(menuList);
        }
        logger.info("连接数据库获取菜单数据成功，返回数据为空");
        return null;
    }


    /**
     * 得到菜单树状图
     *
     * @param menusList
     * @return
     */

    public List<AccountMenu> menusTree(List<AccountMenu> menusList) {
        List<AccountMenu> rootTrees = new ArrayList<AccountMenu>();//声明权限菜单集合，将集合中的父子关系按照 父--- 子集和(childrens)
        for (AccountMenu tree : menusList) {
            //首先将父级集合装入集合中
            if ("-1".equals(tree.getParentNo())) {
                rootTrees.add(tree);
            }
            //循环菜单集合将具有父子关系的子数据装进子集和中
            for (AccountMenu t : menusList) {
                if (t.getParentNo().equals(tree.getMenuNo())) {
                    if (tree.getChildrens() == null) {
                        List<AccountMenu> myChildrens = new ArrayList<AccountMenu>();
                        myChildrens.add(t);
                        tree.setChildrens(myChildrens);
                    } else {
                        tree.getChildrens().add(t);
                    }
                }
            }
            //进行子级排序(同级之间排序)
            sortMenuList(tree.getChildrens());
        }
        //进行菜单父级排序(同级之间排序)
        sortMenuList(rootTrees);
        logger.info("菜单数据父子关系处理成功，返回数据：" + JSONObject.toJSONString(rootTrees));
        return rootTrees;
    }


    /**
     * sortMenuList(菜单同级排序方法（父子级）)
     *
     * @param rootTrees
     * @return
     */

    private static void sortMenuList(List<AccountMenu> rootTrees) {
        int menuSize = rootTrees.size();
        AccountMenu menu1;
        AccountMenu menu2;
        for (int i = 0; i < rootTrees.size() - 1; i++) {
            for (int j = 0; j < menuSize - 1; j++) {
                String order1 = rootTrees.get(j).getSort().toString();
                String order2 = rootTrees.get(j + 1).getSort().toString();
                menu1 = rootTrees.get(j);
                menu2 = rootTrees.get(j + 1);
                if (order1.compareToIgnoreCase(order2) >= 0) {
                    rootTrees.set(j, menu2);
                    rootTrees.set(j + 1, menu1);
                }
            }
            menuSize = menuSize - 1;
        }
    }

    /**
     * 显示/隐藏
     *
     * @param type，menuNos
     * @return
     */

    @Override
    public int showOrVisable(String type, List<String> menuNos) throws BusinessException {
        logger.info("开始连接数据库" + EnumIsShow.getEnum(type).getMessage() + "机构数据，参数信息：type：" + type + ",debtNo:" + menuNos);
        int i = menuMapper.showOrVisable(type, menuNos);
        if (i > 0) {
            logger.info(EnumIsShow.getEnum(type).getMessage() + "菜单数据成功，" + EnumIsShow.getEnum(type).getMessage() + "更新数量：" + i);
            return i;
        }
        logger.info(EnumIsShow.getEnum(type).getMessage() + "菜单数据失败，" + EnumIsShow.getEnum(type).getMessage() + "更新数量：0");
        return 0;
    }

    /**
     * 校验菜单名称/链接/标识是否存在
     *
     * @param accountMenuRequestModel
     * @return
     */

    @Override
    public boolean checkMenu(AccountMenuRequestModel accountMenuRequestModel) {
        logger.info("开始连接数据库校验该字典名称是否存在，参数信息：" + JSONObject.toJSONString(accountMenuRequestModel));
        int count = menuMapper.checkMenu(accountMenuRequestModel);
        if (count > 0) {
            logger.info("该菜单校验不通过！");
            return true;
        }
        logger.info("该菜单校验通过!");
        return false;
    }

    /**
     * 根据菜单编号查询菜单信息
     *
     * @param menuNo
     * @return
     */

    @Override
    public AccountMenu queryMenuById(String menuNo) {
        logger.info("开始连接数据库根据菜单编号查询菜单数据，参数信息：menuNo：" + menuNo);
        AccountMenu menu = menuMapper.selectByPrimaryKey(menuNo);
        if (null != menu) {
            logger.info("根据菜单编号查询菜单数据成功，返回数据信息：" + JSONObject.toJSONString(menu));
//            if (dic.getUpdateDate() != null) {
//                dic.setUpdateDateStr(DateUtils.date2str(dic.getUpdateDate(),DateUtils.DATE_FORMAT_LONG));
//            }
            return menu;
        }
        logger.info("根据菜单编号查询菜单数据失败，该菜单信息不存在！");
        return null;
    }

    /**
     * 保存菜单数据
     *
     * @param model
     * @return
     */

    @Override
    public int save(AccountMenuRequestModel model) {
        logger.info("开始连接数据库保存菜单数据，参数信息：" + JSONObject.toJSONString(model));
        int count = menuMapper.insert(newAccountMenut(model));
        if (count > 0) {
            logger.info("保存菜单数据成功，保存数量：" + count);
            return count;
        }
        logger.info("保存菜单数据失败，保存数量：0");
        return 0;
    }

    /**
     * 修改菜单数据
     *
     * @param model
     * @return
     */

    @Override
    public int update(AccountMenuRequestModel model) {
        logger.info("开始连接数据库修改菜单数据，参数信息：" + JSONObject.toJSONString(model));
        AccountMenu menu = newAccountMenut(model);
        int count = menuMapper.updateByPrimaryKey(menu);
        if (count > 0) {
            logger.info("修改菜单数据成功，修改数量：" + count);
            return count;
        }
        logger.info("修改菜单数据失败，修改数量：0");
        return 0;
    }

    @Override
    public List<AccountMenu> queryRoleMenuList(AccountMenu menu) {
        return menuMapper.queryRoleMenuList(menu);
    }

    /**
     * 得到AccountMenu对象
     *
     * @param model
     * @return
     */

    public AccountMenu newAccountMenut(AccountMenuRequestModel model) {
        AccountUser accountUser = loginCommon.getCurrentUser();
        String currentLoginUser = "";
        if (null != accountUser) {
            currentLoginUser = accountUser.getLoginName();
        }
        AccountMenu accountMenu =  menuMapper.selectByPrimaryKey(model.getMenuNo());
        if (null != accountMenu) {
            //表示修改 可做字段拓展
            CommonBeanUtils.copyPropertiesElseList(model, accountMenu, ElseFiledsUtils.elseFileds(EnumModelType.MODEL_MENU.code()));
            accountMenu.setUpdateBy(currentLoginUser);
            accountMenu.setUpdateDate(new Date());
        } else {
            accountMenu = new AccountMenu();
            //表示新增 可做字段拓展
            CommonBeanUtils.copyProperties(model, accountMenu);
            accountMenu.setDelFlag(EnumDelFlag.DELETE_FLAG.getCode());//未删除
            accountMenu.setIsShow(EnumIsShow.SHOW.getCode());//可见
            accountMenu.setCreateBy(currentLoginUser);
            accountMenu.setCreateDate(new Date());
        }
        //做拓展：如果以后页面改造成保存list修改的话，可把 list.add(accountDic);放在这里
        return accountMenu;
    }


}
