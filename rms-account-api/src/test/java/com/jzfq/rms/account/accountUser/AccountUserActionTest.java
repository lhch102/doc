package com.jzfq.rms.account.accountUser;

import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "file:src/main/resources/spring/applicationContext.xml" }) // 加载配置文件
public class AccountUserActionTest {

    @Before
    public void beforeTest() {

        System.out.println("测试之前操作");
    }

    @Autowired
    private AccountUserService service;

    @Test
    public void resetPasswordTest() {
//        try {
//            service.resetPassword("YH000002", "admin");
//        } catch (BusinessException e) {
//            e.printStackTrace();
//        }
    }

    @After
    public void afterTest() {

        System.out.println("测试之后");
    }
}