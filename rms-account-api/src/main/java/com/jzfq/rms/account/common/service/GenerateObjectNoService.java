package com.jzfq.rms.account.common.service;

import com.jzfq.rms.account.dao.*;
import com.jzfq.rms.account.utils.StringUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生成各种编号
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/4 10:31
 */
@Service
public class GenerateObjectNoService {

    @Autowired
    private AccountUserMapper userMapper;
    @Autowired
    private AccountRoleMapper roleMapper;
    @Autowired
    private AccountMenuMapper menuMapper;
    @Autowired
    private AccountDeptMapper deptMapper;
    @Autowired
    private AccountSystemMapper systemMapper;

    /**
     * 生成用户编号
     *
     * @return
     */
    public String generateUserNo() {
        return generateNo(0);
    }

    /**
     * 生成角色编号
     *
     * @param systemNo 系统编号
     * @return
     */
    public String generateRoleNo(String systemNo) {
        return generateNo(1);
    }

    /**
     * 生成菜单编号
     *
     * @param systemNo 系统编号
     * @return
     */
    public String generateMenuNo(String systemNo) {
        return generateNo(2);
    }

    /**
     * 生成机构编号
     *
     * @return
     */
    public String generateDeptNo() {
        return generateNo(3);
    }

    /**
     * 生成系统编号
     *
     * @return
     */
    public String generateSystemNo() {
        return generateNo(4);
    }

    /**
     * 生成编号
     *
     * @param type 生成类型
     */
    public String generateNo(int type) {
        String No_ = null;
        String str = null;
        switch (type) {
            case 0:
                // 用户
                str = "YH";
                No_ = userMapper.getMaxUserNo();
                break;
            case 1:
                // 角色
                str = "JS";
                No_ = roleMapper.getMaxRoleNo();
                break;
            case 2:
                //菜单
                str = "CD";
                No_ = menuMapper.getMaxMenuNo();
                break;
            case 3:
                // 机构
                str = "JG";
                No_ = deptMapper.getMaxDeptNo();
                break;
            case 4:
                // 系统
                str = "XT";
                No_ = systemMapper.getMaxSystemNo();
                if (!StringUtil.isEmpty(No_)) {
                    String letter = No_.substring(0, 2);
                    int length = No_.length();
                    int noPlus = Integer.parseInt(No_.substring(length - 3, length));
                    No_ = letter + String.format("%03d", noPlus + 1);
                }else {
                    No_ = str + plus(3);
                }
                return No_;
        }
        if (!StringUtil.isEmpty(No_)) {
            String letter = No_.substring(0, 2);
            int length = No_.length();
            int noPlus = Integer.parseInt(No_.substring(length - 6, length));
            No_ = letter + String.format("%06d", noPlus + 1);
        } else {
            No_ = str + plus(6);
        }
        return No_;
    }

    private static int i = 0;

    /**
     * 在初始值上自增1
     *
     * @return
     */
    public static String plus(int numLength) {
        i++;
        String num = null;
        switch (numLength){
            case 3:
                num = "00";
                break;
            case 6:
                num = "00000";
                break;
        }
        return num + i;
    }
}
