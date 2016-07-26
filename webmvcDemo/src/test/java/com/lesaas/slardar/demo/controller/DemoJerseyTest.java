package com.lesaas.slardar.demo.controller;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.example.mvcdemo.MvcDemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MvcDemoApplication.class)//PhoenixQuickStart.class
@WebIntegrationTest(randomPort = true)
public class DemoJerseyTest {
	
	@Value("${local.server.port}")
	private int port;//测试用例的随机端口
	
	private RestTemplate restTemplate = new TestRestTemplate();//RESTFUL 调用模版
	private String demoUri;
	@Rule
	public OutputCapture out = new OutputCapture();//测试控制台输出语句
	
	@Before
	public void setUp(){
		demoUri="http://localhost:"+port+"/slardar/webapi/jerDemo";
		System.out.println("demo Jersey uri:"+demoUri);
	}
	
	@After
	public void tearDown(){
		
	}
	
	/**
	 * get方法测试
	 */
	@Test
	public void getTest(){
		ResponseEntity<String> entity = this.restTemplate.getForEntity(demoUri+"/get", String.class);
		
		Assert.assertSame(entity.getStatusCode(), HttpStatus.OK);
		Assert.assertTrue(entity.getBody().equals("jerDemo"));
		Assert.assertThat(out.toString(),Matchers.containsString("jersey get method"));
	}
	
	/**
	 * post方法测试
	 */
	@Test
	public void postTest(){
		ResponseEntity<String> entity=this.restTemplate.getForEntity(demoUri+"/post", String.class);
		
		Assert.assertSame(entity.getStatusCode(),HttpStatus.OK);
		Assert.assertTrue(entity.getBody().equals("jerPost"));
	}

}
