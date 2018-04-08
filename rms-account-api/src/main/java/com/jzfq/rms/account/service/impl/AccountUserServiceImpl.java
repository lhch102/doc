package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.dao.AccountUserMapper;
import com.jzfq.rms.account.enums.EnumDelFlag;
import com.jzfq.rms.account.service.AccountUserService;
import com.jzfq.rms.account.service.DictionaryService;
import com.jzfq.rms.account.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:46
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {


    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private GenerateObjectNoService service;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public AccountUser queryByUserNameAndPassword(HashMap<String, Object> userMap) {
        return accountUserMapper.queryByUserNameAndPassword(userMap);
    }

    /**
     * 分页查询用户数据
     *
     * @param user
     * @return
     */
    @Override
    public PageData<AccountUser> queryUserList(AccountUser user) {
        //删除flag（0：可用、1：已删除）
        user.setDelFlag(EnumDelFlag.DELETE_FLAG.getCode());
        PageParam<AccountUser> page = new PageParam<>(user, user.getPageNum(), user.getNumPerPage());
        List<AccountUser> userList = accountUserMapper.queryUserList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), userList);
    }

    /**
     * 启用/停用/逻辑删除共用
     * @param userNos
     * @param updateBy
     * @param enableFlag
     * @param delFlag
     */
    @Override
    public void updateUserStatus(List<String> userNos, String updateBy, String enableFlag,String delFlag) {
        AccountUser user = new AccountUser();
        for (String userNo:userNos){
            user.setUserNo(userNo);
            if (delFlag !=null){
                user.setDelFlag(delFlag);
            }else {
                user.setEnableFlag(enableFlag);
            }
            user.setUpdateBy(updateBy);
            user.setUpdateDate(DateUtils.now());
            accountUserMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 添加用户
     * @return
     */
    @Override
    public Map<String, Object> addUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("userNo",service.generateUserNo());
        List<Map<String, Object>> dictList = new ArrayList<>();
        Map<String, Object> dict;
        dict = new LinkedHashMap<>();
        dict.put("label","0");
        dict.put("value","启用");
        dictList.add(dict);
        dict = new LinkedHashMap<>();
        dict.put("label","1");
        dict.put("value","停用");
        dictList.add(dict);
        user.put("enableFlag",dictList);
        //todo
        // dictionaryService.dictList("");
        return user;
    }
}
