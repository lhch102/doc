package com.jzfq.rms.account.accountRole;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountMenu;
import com.jzfq.rms.account.bean.Extended.AccountOperateExtended;
import com.jzfq.rms.account.service.AccountOperateService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = {"file:src/main/resources/spring/applicationContext.xml"}) // 加载配置文件
public class AccountOperateJunitTest {

    @Autowired
    private AccountOperateService accountOperateService;

    @Before
    public void beforeTest() {

        System.out.println("测试之前操作");
    }

    @org.junit.Test
    public void test() {


        HashMap<String, Object> menuNoMap = new HashMap<>();
        menuNoMap.put("menuNo", "CD000001");

        AccountOperateExtended accountOperateExtended = accountOperateService.selectExtendByMenuNo(menuNoMap);
        String jsonTest = JSON.toJSONString(accountOperateExtended);
        System.out.print("菜单操作权限测试数据:" + jsonTest);

    }

    @After
    public void afterTest() {

        System.out.println("测试之后");
    }


}
