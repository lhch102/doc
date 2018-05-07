package com.jzfq.rms.account.accountLogin;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "file:src/main/resources/spring/applicationContext.xml" }) // 加载配置文件
public class ValidateCodeJunitTest {


	@Before
	public void beforeTest() {

		System.out.println("测试之前操作");
	}

	@org.junit.Test
	public void test() {



	}

	@After
	public void afterTest() {

		System.out.println("测试之后");
	}










}
