package com.jzfq.rms.account.accountMenu;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountMenu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "file:src/main/resources/spring/applicationContext.xml" }) // 加载配置文件
public class AccountMenuJunitTest {


	@Before
	public void beforeTest() {

		System.out.println("测试之前操作");
	}

	@org.junit.Test
	public void test() {
		AccountMenu accountMenu01=new AccountMenu();
		AccountMenu accountMenu001=new AccountMenu();
		AccountMenu accountMenu0001=new AccountMenu();

		accountMenu01.setMenuName("系统管理");
		accountMenu01.setMenuNo("01");

		accountMenu001.setMenuName("a001");
		accountMenu001.setMenuNo("001");

		accountMenu0001.setMenuName("a0001");
		accountMenu0001.setMenuNo("0001");



		List<AccountMenu>accountMenu0001List=new ArrayList<AccountMenu>();
		accountMenu0001List.add(accountMenu0001);
		accountMenu001.setChildrenList(accountMenu0001List);

		List<AccountMenu>accountMenu001List=new ArrayList<AccountMenu>();
		accountMenu001List.add(accountMenu001);
		accountMenu01.setChildrenList(accountMenu001List);

		System.out.print(JSONObject.toJSONString(accountMenu01));

	}

	@After
	public void afterTest() {

		System.out.println("测试之后");
	}










}
