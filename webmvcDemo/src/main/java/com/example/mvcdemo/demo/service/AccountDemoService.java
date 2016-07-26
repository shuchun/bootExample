package com.example.mvcdemo.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvcdemo.base.util.ToolUtils;
import com.example.mvcdemo.demo.dao.AccountDemoDao;
import com.example.mvcdemo.demo.dao.IAccountDemoRepository;
import com.example.mvcdemo.demo.entity.Account;
import com.example.mvcdemo.demo.pageModel.AccountPageModel;

/**
 * Account service
 * @author ysc
 *
 */
@Service
@Transactional
public class AccountDemoService {
	
	@Autowired
	private IAccountDemoRepository accountRep;//account jpa 接口
	@Autowired
	private AccountDemoDao accountDao;//mybatis dao
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<Account> getList(){
		
		List<Account> accountPageList=accountRep.findAll();
		return accountPageList;
		
	}
	
	/**
	 * 根据id、proid查询数据，mybatis
	 * @param id
	 * @param proId
	 * @return
	 */
	public List<AccountPageModel> getList(String id,String proId){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", id);
		param.put("proId", proId);
		
		List<AccountPageModel> accountPageList=accountDao.getAccountList(param);
		
		return accountPageList;
	}
	
	/**
	 * 根据项目id获取记录集合
	 * @param proId
	 * @return
	 */
	public int findAccountByProId(String proId){
		List<Account> accountList=accountRep.findByProId(proId);
		if(ToolUtils.isNotEmpty(accountList)){
			for(Account account:accountList){
				System.out.println("Account:"+account.getId()+"-"+account.getCreateTime());
			}
		}
		return accountList.size();
	}
	
	/**
	 * 根据id获取记录
	 * @param id
	 * @return
	 */
	public Account getAccountById(String id){
		Account account=accountRep.getById(id);
		if(ToolUtils.isNotEmpty(account)){
			System.out.println("Account:"+account.getId()+"-"+account.getCreateTime());
		}
		
		return account;
	}
	
	/**
	 * 添加account
	 * @return
	 */
	public String  save(AccountPageModel accountPage){
		Account account=new Account();
		if(!ToolUtils.isNotEmpty(accountPage.getId())){
			account.setId(ToolUtils.getUUID());
		}else{
			account.setId(accountPage.getId());
		}
		
		account.setProId(accountPage.getProId());
		account.setName(accountPage.getName());
		account.setCreateTime(new Date());
		account.setBalance(100.0);
		accountRep.save(account);
		System.out.println("Save Account Id:"+account.getId());
		return account.getId();
	}
	
	/**
	 * 删除指定id数据
	 * @param id
	 */
	public void deleteByAccountId(String id){
		accountRep.deleteById(id);
		System.out.println("delete account id:"+id);
	}
	
	
	public void saveList(){
		List<Account> accountList=new ArrayList<Account>();
		accountList=accountRep.findAll();
		for(int i=0;i<accountList.size();i++){
			
			accountList.get(i).setProId("");
		}
		accountRep.save(accountList);

	}

}
