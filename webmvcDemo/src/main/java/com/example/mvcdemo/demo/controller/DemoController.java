package com.example.mvcdemo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mvcdemo.demo.entity.Account;
import com.example.mvcdemo.demo.pageModel.AccountPageModel;
import com.example.mvcdemo.demo.service.AccountDemoService;

/**
 * 
 * @ClassName: DemoController
 * @Description: Controller示例
 * @author: ysc
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	//@Autowired
	//private DemoService demoService;//redis的操作示例
	@Autowired
	private AccountDemoService accountService;//oracle的操作示例
	
	/**
	 * Get method
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "account/index";
	}
	
	/**
	 * 添加页面显示
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public String add(Model model){
		return "account/add";
	}
	
	/**
	 * 添加提交，并且重定向到列表页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addNewAccount(Model model,AccountPageModel pageModel,RedirectAttributes redirect){
		//执行保存
		String id=accountService.save(pageModel);
		System.out.println();
		//重定向到列表页面
		return "redirect:/demo/list";
	}
	
	
	/**
	 * 列表页面显示
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Model model){
		
		List<Account> list=accountService.getList();
		model.addAttribute("list", list);
		
		return "account/list";
	}
	
}
