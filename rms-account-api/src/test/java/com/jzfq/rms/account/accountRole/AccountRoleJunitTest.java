package com.jzfq.rms.account.accountRole;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.bean.Extended.AccountRoleEditExtended;
import com.jzfq.rms.account.service.AccountRoleService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = {"file:src/main/resources/spring/applicationContext.xml"}) // 加载配置文件
public class AccountRoleJunitTest {

    @Autowired
    private AccountRoleService accountRoleService;

    @Before
    public void beforeTest() {

        System.out.println("测试之前操作");
    }

    @org.junit.Test
    public void roleAdd() {

        //角色新增
        String jsonTest = "";
        String roleNo = "";

        AccountRoleEditExtended accountRoleEditExtended = accountRoleService.getRoleOperatesAll(roleNo);
        jsonTest = JSON.toJSONString(accountRoleEditExtended);

        System.out.print("新增角色，接口返回结果" + jsonTest);
    }

    @org.junit.Test
    public void test() {

        String jsonTest = "";
        String roleNo = "JS000001";

        AccountRoleEditExtended accountRoleEditExtended = accountRoleService.getRoleOperatesAll(roleNo);
        jsonTest = JSON.toJSONString(accountRoleEditExtended);
        System.out.print("修改角色，接口返回结果" + jsonTest);

    }

    @After
    public void afterTest() {

        System.out.println("测试之后");
    }


}
