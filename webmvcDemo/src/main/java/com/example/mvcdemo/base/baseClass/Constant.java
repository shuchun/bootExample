package com.example.mvcdemo.base.baseClass;

import com.example.mvcdemo.base.util.ResourceUtil;


/**
 * @see(功能介绍) :公共静态变量定义
 * @version(版本号): 1.0
 * @date(创建日期): 2014-6-14
 * @author(创建人): 
 */
public class Constant {
	/**
	 * 通用类静态变量
	 */
	public static final String PRO_PROINFO = "1";//项目
	public static final String PRO_CATEGORY = "2";//类别
	public static final String PRO_SUBJECTS = "3";//科目
	public static final String PRO_SINGLE = "4";//单科项目
	public static final String URLSIGN="f8d1edfbe809973b"; //url默认地址与参数分隔符
	public static final String SYS_SWICH="99"; //url默认地址与参数分隔符
	public static final String SYS_MENUSJ="1301"; //上级机构信息id
	
	public static final int PAG_PAGENUMBER = 1;//页面默认起始页
	public static final int PAG_PAGESIZE   = 15;//默认每页显示条数
	
	/**redis 锁定静态变量*/
	public static final int LOCK_TIME=300;
	public static final int COUNT_TIME=120;
	public static final int SESSION_TIME_OUT=1180;

	/**
	 * 服务端资源地址（davidson）静态变量
	 */
	public static final String SERVICE_URL=ResourceUtil.getRemoteUrl();
	
	public static final String SERVICE_FILE_URL="http://localhost:8089/davidson/www";

	/**
	 * 支付类型payType静态变量
	 */
	public static final String PAYTYPE_DIRECT_PAYMENT ="1";//直接支付
	public static final String PAYTYPE_RECHARGE_PAYMENT ="2";//预付充值
	public static final String PAYTYPE_BALANCE_PAYMENT ="3";//余额支付
	public static final String PAYTYPE_DIRECT_BALANCE_PAYMENT ="4";//直接+余额支付
	public static final String PAYTYPE_RETURN_BALANCE_PAYMENT ="5";//退回余额
	public static final String PAYTYPE_RETURN_BANKCARD_PAYMENT ="6";//余额退卡
	public static final String PAYTYPE_ORG_PAYMENT ="7";//机构支付
	public static final String PAYTYPE_ORG_REFUND_PAYMENT ="8";//机构退费
	public static final String PAYTYPE_COLLECTIVE_REGISTRATION_PAYMENT ="9";//集体报名支付
	public static final String PAYTYPE_MAIL_PAYMENT ="10";//邮寄费支付
	public static final String PAYTYPE_VOUCHERS_PAYMENT ="11";//代金券支付
	public static final String PAYTYPE_BALANCE_VOUCHERS_PAYMENT ="12";//余额+代金券支付
	public static final String PAYTYPE_DIRECT_VOUCHERS_PAYMENT ="13";//直接+代金券支付
	public static final String PAYTYPE_DIRECT_BALANCE_VOUCHERS_PAYMENT ="14";//直接+余额+代金券支付
	
	/**
	 * (特殊类 ： 通用报名)流水交易类型
	 */
	public static final String TRADETYPE_A =	"a";	//支付费用：（支付考试费、资料费、充值等，属于增加金额）
	public static final String TRADETYPE_B =	"b";	//使用费用：
	public static final String TRADETYPE_C =	"c";	//退回费用：
	public static final String TRADETYPE_D =	"d";	//手续费：
	public static final String TRADETYPE_E =	"e";	//分账：
	public static final String TRADETYPE_F =	"f";	//退回余额
	public static final String TRADETYPE_G =	"g";	//集体报名支付（创建账号时生成的系统内部假流水）
	public static final String TRADETYPE_H =	"h";	//预付充值
	public static final String TRADETYPE_I =	"i";		//预付失败
	public static final String TRADETYPE_K =	"k";	//集体报名退费（同样是内部假流水与G类似）
	public static final String TRADETYPE_M =	"m";	//考试券支付
	public static final String TRADETYPE_X =	"x";	//机构支付
	public static final String TRADETYPE_Y =	"y";	//机构退费
	public static final String TRADETYPE_S =	"s";	//分账(0:失败，1:成功，x:等待银行结果) 
	

}





