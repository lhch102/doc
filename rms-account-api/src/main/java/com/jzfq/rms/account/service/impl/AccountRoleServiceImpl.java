package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.*;
import com.jzfq.rms.account.bean.Extended.AccountRoleEditExtended;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.dao.*;
import com.jzfq.rms.account.enums.EnumDelFlag;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    private AccountMenuService accountMenuService;
    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Autowired
    private AccountDicMapper accountDicMapper;
    @Autowired
    private SystemService systemService;
    @Autowired
    private AccountMenuOperateService accountMenuOperateService;
    @Autowired
    private AccountOperateMapper accountOperateMapper;

    @Autowired
    private AccountRoleMenuService accountRoleMenuService;

    @Autowired
    private GenerateObjectNoService generateObjectNoService;

    private final static String DELE_FLAG_TYPE = "common_delete_flag";

    /**
     * 角色编辑/新增逻辑
     *
     * @param roleNo
     * @return
     */
    @Override
    public AccountRoleEditExtended getRoleOperatesAll(String roleNo) {

        AccountRoleEditExtended accountRoleEditExtended = new AccountRoleEditExtended();
        AccountMenu accountMenuParam = null;

        try {
            List<AccountMenu> accountMenus = null;
            List<AccountSystem> accountSystems = null;
            List<AccountDic> accountDics = accountDicMapper.queryDicListByType(DELE_FLAG_TYPE);

            String systemNo = "";

            //修改
            if (StringUtils.isNotBlank(roleNo)) {
                AccountRole accountRole = this.selectByPrimaryKey(roleNo);
                if (accountRole != null) {
                    systemNo = accountRole.getSystemNo();

                    if (StringUtils.isNotBlank(systemNo)) {
                        //根据系统编号，获取菜单信息
                        accountMenuParam = new AccountMenu();
                        accountMenuParam.setMenuNo(systemNo);
                        accountMenuParam.setRoleNo(roleNo);
                        accountMenus = accountMenuService.queryRoleMenuList(accountMenuParam);
                        //系统
                        AccountSystem accountSystem = systemService.selectByPrimaryKey(systemNo);
                        accountSystems = new ArrayList<AccountSystem>();
                        accountSystems.add(accountSystem);
                    }
                    accountRoleEditExtended.setAccountRole(accountRole);
                }
            } else {
                //新增
                accountSystems = systemService.selectAll();
                if (accountSystems != null && accountSystems.size() > 0) {
                    AccountSystem accountSystem = accountSystems.get(0);
                    if (accountSystem != null) {
                        systemNo = accountSystem.getSystemNo();
                        accountMenuParam = new AccountMenu();
                        accountMenuParam.setSystemNo(systemNo);
                        accountMenus = accountMenuService.queryMenuList(accountMenuParam);
                        accountSystems.clear();
                        accountSystems.add(accountSystem);
                    }
                }
            }

            accountRoleEditExtended.setAccountDic(accountDics);
            accountRoleEditExtended.setAccountMenus(accountMenus);
            accountRoleEditExtended.setAccountSystem(accountSystems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountRoleEditExtended;
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public int updateByPrimaryKeySelective(AccountRole record) {
        return accountRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageData<AccountRole> queryRoleList(AccountRole role) throws BusinessException {

        PageParam<AccountRole> page = new PageParam<>(role, role.getPageNum(), role.getNumPerPage());
        List<AccountRole> roleList = accountRoleMapper.queryRoleList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), roleList);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void updateEnableByPrimaryKey(AccountRole accountRole) {
        accountRoleMapper.updateEnableByPrimaryKey(accountRole);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public int insert(AccountRole record) throws BusinessException {
        return accountRoleMapper.insert(record);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(AccountRole record) throws Exception {
        try {
            String deptOper = record.getDeptOper();
            if (StringUtils.isBlank(record.getRoleNo())) {
                String roleNo = generateObjectNoService.generateNo(1);
                record.setRoleNo(roleNo);
                this.insert(record);
            } else {
                this.updateByPrimaryKeySelective(record);
            }
            /**
             * 以下保存 角色相关的 关系表
             */
            AccountRoleMenuKey accountRoleMenuKey = new AccountRoleMenuKey();
            accountRoleMenuKey.setRoleNo(record.getRoleNo());
            accountRoleMenuService.deleteByPrimaryKey(accountRoleMenuKey);

            //保存菜单和角色的关系
            if (record.getMenuNos() != null) {
                for (String menNo : record.getMenuNos()) {
                    try {
                        accountRoleMenuKey.setMenuNo(menNo);
                        accountRoleMenuKey.setRoleNo(record.getRoleNo());
                        accountRoleMenuService.insert(accountRoleMenuKey);

                        //保存机构权限和菜单的关系
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("operateType", "2");//2= 机构类型
                        hashMap.put("menuNo", menNo);

                        //删除菜单下的机构
                        //添加菜单下的机构
                        AccountMenuOperateKey accountMenuOperateKey = new AccountMenuOperateKey();
                        accountMenuOperateKey.setMenuNo(menNo);

                        List<AccountOperate> accountOperates = accountOperateMapper.selectByType(hashMap);
                        if (accountOperates != null && accountOperates.size() > 0) {
                            AccountOperate accountOperate = accountOperates.get(0);
                            if (accountOperate != null) {
                                accountMenuOperateKey.setId(accountOperate.getId());
                                hashMap.put("id", accountOperate.getId());
                                accountMenuOperateService.deleteByMenuNo(hashMap);
                            }
                        }

                        //添加选中的值
                        hashMap.put("operateValue", record.getDeptOper());
                        List<AccountOperate> accountOperateAdd = accountOperateMapper.selectByType(hashMap);
                        if (accountOperateAdd != null && accountOperateAdd.size() > 0) {
                            Long operId = accountOperateAdd.get(0).getId();
                            accountMenuOperateKey.setId(operId);
                            accountMenuOperateService.insert(accountMenuOperateKey);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception(e);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = false,rollbackFor = BusinessException.class)
    @Override
    public void updateEnable(HashMap<String, Object> hashMap) throws BusinessException {
        accountRoleMapper.updateEnable(hashMap);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateDelFlag(HashMap<String, Object> hashMap) {
        accountRoleMapper.updateDelFlag(hashMap);
    }

    @Override
    public AccountRole queryByRoleName(HashMap<String, Object> hashMap) {
        return accountRoleMapper.queryByRoleName(hashMap);
    }

    /**
     * 根据用户分页查询角色列表
     *
     * @param accountRole
     * @return
     */
    @Override
    public PageData<AccountRole> queryRoleByUser(AccountRole accountRole) {
        //删除flag（0：可用、1：已删除）
        accountRole.setEnableFlag(EnumDelFlag.DELETE_FLAG.getCode());
        PageParam<AccountRole> page = new PageParam<>(accountRole, accountRole.getPageNum(), accountRole.getNumPerPage());
        List<Map<String, Object>> roleList = accountRoleMapper.queryRoleByUser(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), roleList);
    }


    @Override
    public AccountRole selectByPrimaryKey(String roleNo) {
        return accountRoleMapper.selectByPrimaryKey(roleNo);
    }
}
