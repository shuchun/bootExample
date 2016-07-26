package com.lesaas.slardar.demo.service;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.mvcdemo.MvcDemoApplication;
import com.example.mvcdemo.demo.entity.Account;
import com.example.mvcdemo.demo.pageModel.AccountPageModel;
import com.example.mvcdemo.demo.service.AccountDemoService;

/**
 * 
 * @ClassName: AccountDemoServiceTest
 * @Description: service层测试
 * @author: ysc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MvcDemoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDemoServiceTest {
	
	@Autowired
	private AccountDemoService accountService;
	private AccountPageModel accountPage;
	private final String SAVE_ID="tid";
	private final String TEST_PRO_ID="t1";
	
	@Rule
	public OutputCapture out = new OutputCapture();//测试控制台输出语句
	
	@Before
	public void setUp(){
		//init
		accountPage=new AccountPageModel();
		accountPage.setId(SAVE_ID);
		accountPage.setProId(TEST_PRO_ID);
		accountPage.setName("testName");
		accountPage.setBalance(100.0);
	}
	
	@After
	public void tearDown(){
		//finally 
	}
	
	/**
	 * 保存方法测试
	 */
	@Test
	public void accountSaveTest(){
		//first exect method
		String id=accountService.save(accountPage);
		
		Assert.assertNotNull(id);
		Assert.assertTrue(id.equals(SAVE_ID));
		Assert.assertThat(out.toString(), Matchers.containsString("Save Account Id:"+id));
	}
	
	/**
	 * proId条件查询测试
	 */
	@Test
	public void findAccountByProIdTest(){
		int rows=accountService.findAccountByProId(TEST_PRO_ID);
		
		Assert.assertNotNull(rows);
		Assert.assertSame(rows, 1);
	}
	
	/**
	 * id条件查询
	 */
	@Test
	public void getAccountByIdTest(){
		Account account=accountService.getAccountById(SAVE_ID);
		
		Assert.assertNotNull(account);
		Assert.assertTrue(account.getProId().equals(TEST_PRO_ID));
	}
	
	/**
	 * 删除测试
	 */
	@Test
	public void zDeleteByAccountIdTest(){
		accountService.deleteByAccountId(SAVE_ID);
		
		Assert.assertThat(out.toString(), Matchers.containsString("delete account id:"+SAVE_ID));
	}

}
