/**
 * Tech Ltd.
 */
package com.example.mvcdemo.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @see(功能介绍): 账户余额表信息
 * @date(创建日期): 2016-02-24
 * @author(创建人): yxy
 */

@Entity
@Table(name = "PAY_ACCOUNT")
public class Account {

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

	@Id
	@Column(name="id")
	public String getId() {
		return id;
	} 

	public void setId(String id) {
		this.id=id;
	} 

	@Column(name="pro_id")
	public String getProId() {
		return proId;
	} 

	public void setProId(String proId) {
		this.proId=proId;
	} 
	
	@Column(name="name")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	} 

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	} 

	@Column(name="balance")
	public Double getBalance() {
		return balance;
	} 

	public void setBalance(Double balance) {
		this.balance=balance;
	} 

	@Column(name="account_frozen")
	public String getAccountFrozen() {
		return accountFrozen;
	} 

	public void setAccountFrozen(String accountFrozen) {
		this.accountFrozen=accountFrozen;
	} 

	@Column(name="frozen_money")
	public Double getFrozenMoney() {
		return frozenMoney;
	} 

	public void setFrozenMoney(Double frozenMoney) {
		this.frozenMoney=frozenMoney;
	} 

	@Column(name="available_balance")
	public Double getAvailableBalance() {
		return availableBalance;
	} 

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance=availableBalance;
	} 

} 