package com.example.mvcdemo.demo.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.mvcdemo.demo.pageModel.AccountPageModel;
import com.example.mvcdemo.demo.service.AccountDemoService;

/**
 * 
 * @ClassName: DemoJersey
 * @Description: RESTFUL WebService jersey示例
 * @author: ysc
 *
 */
@Path("/jerDemo")
public class DemoJersey {
	
	@Autowired
	private AccountDemoService accountService;//oracle的操作示例

	/**
	 * RESTFUL Web Service get 方法示例
	 * @return
	 */
	@Path("/get")
	@GET
	public String get(){
		System.out.println("jersey get method");
		return "jerDemo";
	}
	
	/**
	 * RESTFUL Web Service post 方法示例
	 * @return
	 */
	@Path("/post")
	@POST
	public String post(){
		return "jerPost";
	}
	
	/**
	 * @return 
	 * 
	 */
	@Path("/getAccount/{proId:\\w+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<AccountPageModel> getAccountByProId(@PathParam("proId")String proId){
		return accountService.getList(null, proId);
	}
	
}
