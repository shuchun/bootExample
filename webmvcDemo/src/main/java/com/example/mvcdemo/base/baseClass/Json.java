package com.example.mvcdemo.base.baseClass;

public class Json {

	private boolean success = false;

	private String msg = "";

	private Object obj = null; 
	
	private String studentId;
	
	private String payTypePage;
	
	private String bankUrl;
	
	private String examVoucherMoney;
	
	private String balanceMoney;
	
	private String payMoney;
	
	private String tranData;
	
	private String merSignMsg;
	
	private String merCert;
	
	
	
	
	public String getTranData() {
		return tranData;
	}

	public void setTranData(String tranData) {
		this.tranData = tranData;
	}

	public String getMerSignMsg() {
		return merSignMsg;
	}

	public void setMerSignMsg(String merSignMsg) {
		this.merSignMsg = merSignMsg;
	}

	public String getMerCert() {
		return merCert;
	}

	public void setMerCert(String merCert) {
		this.merCert = merCert;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getExamVoucherMoney() {
		return examVoucherMoney;
	}

	public void setExamVoucherMoney(String examVoucherMoney) {
		this.examVoucherMoney = examVoucherMoney;
	}

	public String getBalanceMoney() {
		return balanceMoney;
	}

	public void setBalanceMoney(String balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPayTypePage() {
		return payTypePage;
	}

	public void setPayTypePage(String payTypePage) {
		this.payTypePage = payTypePage;
	}

	public String getBankUrl() {
		return bankUrl;
	}

	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}

	
	
}
