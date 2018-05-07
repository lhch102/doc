package com.jzfq.rms.account.accountLogin;

import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.LoginCommon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = {"file:src/main/resources/spring/applicationContext.xml"}) // 加载配置文件
public class LoginJunitTest {

    @Autowired
    private LoginCommon loginCommon;

    @Before
    public void beforeTest() {

        System.out.println("测试之前操作");
    }

    @org.junit.Test
    public void test() {
        AccountUser accountUser = loginCommon.getCurrentUser();
        if (accountUser != null) {
            System.out.print(accountUser.getLoginName());
        }
    }

    @After
    public void afterTest() {

        System.out.println("测试之后");
    }


}
