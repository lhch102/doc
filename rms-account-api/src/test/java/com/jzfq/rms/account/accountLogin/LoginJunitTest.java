package com.jzfq.rms.account.accountLogin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "file:src/main/resources/spring/applicationContext.xml" }) // 加载配置文件
public class LoginJunitTest {

	@Before
	public void beforeTest() {

		System.out.println("测试之前操作");
	}

	@org.junit.Test
	public void Test() {

	}

	@After
	public void afterTest() {

		System.out.println("测试之后");
	}










}
