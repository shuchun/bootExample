package com.example.mvcdemo.demo.controller;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.mvcdemo.MvcDemoApplication;
import com.example.mvcdemo.demo.pageModel.AccountPageModel;

/**
 * 
 * @ClassName: DemoControllerTest
 * @Description: controller层测试
 * @author: ysc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MvcDemoApplication.class)
@WebIntegrationTest(randomPort = true)
public class DemoControllerTest {
	
	@ClassRule
	public static OutputCapture out = new OutputCapture();
	
	@Autowired  
    private WebApplicationContext wac;  
  
    private MockMvc mockMvc; 
    
    @Before
    public void setUp(){
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); 
    }
    
    @After
    public void tearDown(){
    	
    }
	
    /**
     * 首页测试
     * @throws Exception 
     */
    @Test
    public void indexTest() throws Exception{
    	mockMvc.perform(MockMvcRequestBuilders.get("/demo"))
    		   .andExpect(MockMvcResultMatchers.status().isOk())//200状态
    		   .andDo(MockMvcResultHandlers.print());
    	Assert.assertThat(out.toString(), Matchers.containsString("View name = account/index"));
    }
    
    /**
     * 添加页面测试
     * @throws Exception 
     */
    @Test
    public void addGetTest() throws Exception{
    	mockMvc.perform(MockMvcRequestBuilders.get("/demo/add"))
    	.andExpect(MockMvcResultMatchers.status().isOk())//200状态
    	.andDo(MockMvcResultHandlers.print());
    	
    	Assert.assertThat(out.toString(), Matchers.containsString("View name = account/add"));
    }
    
    /**
     * 添加提交测试
     * @throws Exception 
     */
    @Test
    public void addPostTest() throws Exception{
    	AccountPageModel param=new AccountPageModel();
    	param.setProId("p1");
    	param.setName("ysc");
    	param.setCreateTime(new Date());
    	param.setBalance(100.0);
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/demo/add/", param))
    	.andExpect(MockMvcResultMatchers.status().is3xxRedirection())//302重定向
    	.andDo(MockMvcResultHandlers.print());
    	
    	Assert.assertThat(out.toString(), Matchers.containsString("View name = redirect:/demo/list"));
    }
    
    /**
     * 列表页测试
     * @throws Exception 
     */
    @Test
    public void listTest() throws Exception{
    	
    	mockMvc.perform(MockMvcRequestBuilders.get("/demo/list"))
    	.andExpect(MockMvcResultMatchers.status().isOk())
    	.andDo(MockMvcResultHandlers.print());
    	
    	Assert.assertThat(out.toString(), Matchers.containsString("View name = account/list"));
    	
    }
	
	
}
