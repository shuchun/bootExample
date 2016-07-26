package com.example.mvcdemo.demo.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.mvcdemo.demo.entity.Account;

/**
 * account jpa 接口
 * @author ysc
 *
 */
public interface IAccountRepository extends Repository<Account, String> {
	
	/**
	 * 根据项目id获取集合
	 * @param proId
	 * @return
	 */
	List<Account> findByProId(String proId);
	/**
	 * 根据id获取account记录
	 * @param id
	 * @return
	 */
	Account getById(String id);
	/**
	 * 保存
	 * @param account
	 * @return
	 */
	Account save(Account account);
	/**
	 * 删除
	 * @param account
	 */
	void delete(Account account);
	/**
	 * 删除制定idaccount
	 * @param id
	 */
	void deleteById(String id);

}
