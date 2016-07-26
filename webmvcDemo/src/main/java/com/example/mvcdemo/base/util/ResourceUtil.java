package com.example.mvcdemo.base.util;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author  
 * 
 */
public class ResourceUtil {
	
	
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("application_"+System.getProperty("profilesName"));
	private static final ResourceBundle bankBundle = java.util.ResourceBundle.getBundle(bundle.getString("bankParam")+"_"+System.getProperty("profilesName"));
	
	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}
	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getbasUrl() {
		return bundle.getString("bas_url");
	}

	/**
	 * 获取发送密码时显示的系统邮箱
	 * @return
	 */
	public static final String getRootMail(){
		return bundle.getString("root_mail");
	}
	
	/**
	 * 获取发送密码时的需要显示的站点网址
	 * @return
	 */
	public static final String getWebSite(){
		return bundle.getString("website");
	}
	
	/**
	 * 获取远程访问地址
	 * @return
	 */
	public static final String getRemoteUrl(){
		return bundle.getString("remote_url");
	}
	
	/**
	 * 获取远程文件访问地址（供web使用）
	 * @return
	 */
	public static final String getWebRemoteFileUrl(){
		return bundle.getString("remote_file_url");
	}
	
	/**
	 * 获取发送邮件模板的地址
	 * @return 发送邮件模板的地址
	 */
	public static final String getMailTemplate(){
		return bundle.getString("mail_template");
	}
	
	/**
	 * 获取新建用户的重置密码邮箱模版
	 * @return
	 */
	public static final String getUserMailTemplate(){
		return bundle.getString("mail_user");
	}
	
	/**
	 * 获取站内信箱的模版
	 * @return
	 */
	public static final String getLetterMailTemplate(){
		return bundle.getString("mail_letter");
	}
	
	/**
	 * 获得上传表单域的名称
	 * 
	 * @return
	 */
	public static final String getUploadFieldName() {
		return bundle.getString("uploadFieldName");
	}

	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		return Long.valueOf(bundle.getString("uploadFileMaxSize"));
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return bundle.getString("uploadFileExts");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return bundle.getString("uploadDirectory");
	}
	
	/**
	 * 获得Excel要放到那个目录
	 */
	public static final String getExcelDirectory() {
		return bundle.getString("excelDirectory");
	}
	/**
	 * 获得Txt相对路径
	 */
	public static final String getTxtUrl() {
		return bundle.getString("txtUrl");
	}
	/**
	 *文件路径 
	 */
	public static final String getBaseFilePath(){
		return bundle.getString("base_filePath");
	}
	/**
	 *BEC_logoUrl 
	 */
	public static final String getBECLogoUrl(){
		return bundle.getString("BEC_logoUrl");
	}
	/**
	 *BEC_网址 
	 */
	public static final String getBECWebAddress(){
		return bundle.getString("BEC_webAddress");
	}
	
	/**
	 * 是否使用debuglog
	 * @return
	 */
	public static final boolean getEnv(){
		boolean debug=false;
		try{
			String env=bundle.getString("env");
			if("debug".equals(env)){
				debug= true;
			}
		} catch(Exception e){
			//未设置是否使用debug，默认不起用debug日志，忽略此异常
		}
		return debug;
	}
	
	/**
	 * 获取支付服务的基础路径
	 * @return
	 */
	public static final String getPayBaseUrl(){
		return bundle.getString("payBaseUrl");
	}
	/**
	 * 发送消息日志
	 * @return
	 */
	public static final String getSendLog(){
		return bundle.getString("sendLog");
	}
	
	/**
	 * 签名日志
	 * @return
	 */
	public static final String getSignLog(){
		return bundle.getString("signLog");
	}
	
	/**
	 * 交易流水记录
	 * @return
	 */
	public static final String getTranFlowLog(){
		return bundle.getString("tranFlowLog");
	}
	
	/*--------------------- 银行/商户相关参数信息  -------------------------*/
	
	/**
	 * NC 服务器IP
	 */
	public static final String getNCIP(){
		return bankBundle.getString("NCIp").trim();
	}
	
	/**
	 * NC 服务器加密端口
	 */
	public static final String getNCPort(){
		return bankBundle.getString("NCPort").trim(); //加密端口号
	}
	
	/**
	 * NC 服务器签名端口
	 */
	public static final String getNCSignPort(){
		return bankBundle.getString("NCPort2").trim(); //签名端口号
	}
	
	/**
	 * 商户入账账号
	 * @return
	 */
	public static final String getMerAcct(){
		return bankBundle.getString("merAcct");
	}
	
	/**
	 * 商户入账帐户名
	 * @return
	 */
	public static final String getMerName(){
		String name=bankBundle.getString("merName");
		String source="";
		try {
			source = new String(ToolUtils.decodeByBASE64(name));
			source=new String(ToolUtils.decodeByBASE64(source));
		} catch (IOException e) {
			System.out.println("未设置企业账户户名");
			e.printStackTrace();
		}
		
		return source;
	}
	/**
	 * 商户代码
	 * @return
	 */
	public static final String getMerCode(){
		return bankBundle.getString("merCode");
	}
	/**
	 * 获取企业数据层公钥文件
	 * @return
	 */
	public static final String getCerFile(){
		return bankBundle.getString("cerFile");
	}
	
	/**
	 * 获取企业数据层私钥文件
	 * @return
	 */
	public static final String getCerKey(){
		return bankBundle.getString("cerKey");
	}
	
	/**
	 * 获取企业数据层私钥口令
	 * 获取对账查询密码,考试中心支付平台提供的查询密码，只在使用中心原有的支付系统时有效,
	 * @return
	 */
	public static final String getCerPwd(){
		String pwd=bankBundle.getString("cerPwd");
		String source="";
		try {
			source = new String(ToolUtils.decodeByBASE64(pwd));
			source=new String(ToolUtils.decodeByBASE64(source));
		} catch (IOException e) {
			System.out.println("未设置企业数据层私钥");
			e.printStackTrace();
		}
		
		return source;
	}
	
	/**
	 * 获取证书id,使用工行时
	 * 获取项目Id,考试中心支付平台注册项目号，只在使用中心原有支付系统时有效
	 * @return
	 */
	public static final String getCerId(){
		return bankBundle.getString("cerId");
	}
	
	/**
	 * 获取B2C验签公钥证书
	 * @return
	 */
	public static final String getPublicCer(){
		return bankBundle.getString("publicCerFile");
	}
	
	/**
	 * 获取银行编号
	 * @return
	 */
	public static final String getBankCode(){
		return bankBundle.getString("bankCode");
	}
	/**
	 * 获取集团CIS号
	 * @return
	 */
	public static final String getCIS(){
		return bankBundle.getString("cis");
	}
	/**
	 * 获取证书ID
	 * @return
	 */
	public static final String getID(){
		return bankBundle.getString("id");
	}
	/**
	 * 商城类型
	 * @return
	 */
	public static final String getShopType(){
		return bankBundle.getString("shopType");
	}
	/**
	 * 获取银企查询接口路径
	 * @return
	 */
	public static final String getQueryUrl(){
		return bankBundle.getString("QueryUrl");
	}
	/**
	 * 获取银企互联提交接口路径
	 * @return
	 */
	public static final String getSubmitUrl(){
		return bankBundle.getString("SubmitUrl");
	}
	/**
	 * 获取银企互联路径(查询/提交)中的参数占位符格式
	 * @return
	 */
	public static final String getUrlParam(){
		return bankBundle.getString("urlParam");
	}
	
	/**
	 * 获取是否使用支付中心支付平台
	 * @return
	 */
	public static final boolean getUsePayCenter(){
		
		boolean use=false;
		
		try{
			String usePayCenter=bankBundle.getString("usePayCenter");
			use=Boolean.valueOf(usePayCenter);
		}catch(Exception e){
			//添加容错，看是否使用原有的支付中心支付系统
		}
		return use;
	}
	
	/**
	 * 获取xml模版基础文件地址
	 * @return
	 */
	public static final String getTempDir(){
		return bankBundle.getString("tempDir");
	}
	
	/**
	 * 银行服务器的时间
	 * @return
	 */
	public static final String getServerDate(){
		return bankBundle.getString("serverDate");
	}
	
	/**
	 * 获取magnus地址
	 * @return
	 */
	public static final String getmagnusUrl(){
		return bundle.getString("magnus_url");
	}
	/**
	 * 获取naga地址
	 * @return
	 */
//	public static final String getnagaUrl(){
//		return bundle.getString("naga_url");
//	}
	
	/**
	 * 获取登录地址
	 * @return
	 */
//	public static final String getloginUrl(){
//		return bundle.getString("login_url");
//	}
	
	/**
	 * 获取首页地址
	 */
//	public static final String getindexUrl(){
//		return bundle.getString("index_url");
//	}
	
	/**
	 * 获取lina_url
	 */
//	public static final String getlinaUrl(){
//		return bundle.getString("lina_url");
//	}
	
}
