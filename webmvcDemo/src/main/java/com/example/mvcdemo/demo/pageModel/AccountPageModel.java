package com.example.mvcdemo.demo.pageModel;

import java.util.Date;

/**
 * account pagemodel
 * @author ysc
 *
 */
public class AccountPageModel {
	/*  */
	private String id;

	/*  */
	private String proId;
	
	private String name;

	/*  */
	private Date createTime;

	/*  */
	private Double balance;

	/*  */
	private String accountFrozen;

	/*  */
	private Double frozenMoney;

	/*  */
	private Double availableBalance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccountFrozen() {
		return accountFrozen;
	}

	public void setAccountFrozen(String accountFrozen) {
		this.accountFrozen = accountFrozen;
	}

	public Double getFrozenMoney() {
		return frozenMoney;
	}

	public void setFrozenMoney(Double frozenMoney) {
		this.frozenMoney = frozenMoney;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
