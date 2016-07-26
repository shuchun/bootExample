package com.example.mvcdemo.base.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class ToolUtils {

	/**
	 * 判断是否非空
	 */
	public static boolean isNotEmpty(Object obj) {
		return obj != null && !"".equals(obj.toString());
	}
	public static boolean isLastDay(String lastDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar ld = Calendar.getInstance();
		Date date = sdf.parse(lastDate);
		ld.setTime(date);
		ld.set(Calendar.DATE, 1);
		ld.add(Calendar.MONTH, 1);
		ld.add(Calendar.DATE, -1);
		if (lastDate.equalsIgnoreCase(sdf.format(ld.getTime()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将对像转化成字符串
	 */
	public static String getStr(Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return "";
		}
	}

	public static Integer convertInteger(String str) throws Exception {
		DecimalFormat sd = new DecimalFormat("###");
		if (str != null) {
			return Integer.valueOf(sd.parseObject(str).toString());
		}
		return null;
	}

	/**
	 * 转化分母值
	 * 
	 * @param o
	 * @return
	 */
	public static double convertOtherdouble(Object o) {

		if (o != null && !o.equals("")) {
			return Double.parseDouble(o.toString());
		} else {
			return 1;
		}
	}

	public static Timestamp convertTimestamp(Object o) {

		if (o != null) {
			return Timestamp.valueOf(o.toString());
		} else {
			return null;
		}
	}

	public static String getPreloanSurveyOptionID(String str) {
		String[] s = str.split("\\|");
		if (str.length() > 0) {
			return s[0];
		} else {
			return "";
		}
	}

	public static double getOptionScore(String str) {
		String[] s = str.split("\\|");
		if (str.length() > 1) {
			return Double.parseDouble(s[1]);
		} else {
			return 0;
		}
	}

	/**
	 * 字符串转化成金额
	 * 
	 * @param moneyStr
	 * @return
	 */
	public static Double getMoney(String moneyStr) {
		if (moneyStr == null || "".equals(moneyStr)) {
			return null;
		} else {
			if (moneyStr.indexOf(",") != -1) {
				moneyStr = moneyStr.trim().replaceAll(",", "");
			}
			Double t = Double.valueOf(moneyStr.trim());
			return t;
		}
	}

	/**
	 * 返回项目所在的路径
	 */
	public static String getReportPath() throws IOException {
		String fileName = "runqianReportApplet.jar";
		String projectName = "report";
		@SuppressWarnings("rawtypes")
		Map pathMap = new HashMap();
		String path = new File("..").getCanonicalPath().toString();
		// path = path.replaceAll("\\\\", "/");
		pathMap = getPathMap(pathMap, path, projectName);
		if (null != pathMap.get(fileName)) {
			return pathMap.get(fileName).toString();
		} else {
			return "";
		}
	}

	/**
	 * 返回保存文件名对应路径的map
	 * 
	 * @param pathMap
	 * @param path
	 * @param projectName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getPathMap(Map pathMap, String path, String projectName) {
		File f = new File(path);
		String[] list = f.list();
		for (int i = 0; i < list.length; i++) {
			File t = new File(path + "\\" + list[i]);
			if (t.isDirectory() && t.list().length > 0
					&& list[i].equals(projectName)) {
				String[] lists = t.list();
				for (int j = 0; j < lists.length; j++) {
					File h = new File(path + "\\" + list[i] + "\\" + lists[j]);
					if (h.isFile()) {
						pathMap.put(lists[j], path + "\\" + list[i] + "\\");
					}
				}

			} else if (t.isDirectory() && t.list().length > 0
					&& !list[i].equals(projectName)) {
				pathMap = getPathMap(pathMap, path + "\\" + list[i],
						projectName);
			}
		}
		return pathMap;
	}

	/**
	 * 金额格式
	 */
	public static String convertCurrency(Object o) {
		if (o != null && !o.toString().equals("")) {
			DecimalFormat nf = new DecimalFormat("###,##0.00");
			double d = Double.parseDouble(o.toString());
			return nf.format(d);
		} else {
			return "";
		}
	}

	public static String convertNumber(double o) {
		if (o > 0) {
			DecimalFormat nf = new DecimalFormat("###.##");
			return nf.format(o);
		} else {
			return "";
		}
	}

	public static Double getYearRateByMonth(Double d) {
		return new Double((d.doubleValue() * 1000 * 12) / 10000);
	}

	public static Double getYearRateByDay(Double d) {
		return new Double(d.doubleValue() * 360);
	}

	/**
	 * 
	 * @param dSource
	 * @return
	 */
	public static Double getRound(double dSource) {
		double dResult;

		java.math.BigDecimal bigDecimal = new java.math.BigDecimal(String
				.valueOf(dSource));

		int scale = 6; // 小数点后精度
		dResult = bigDecimal
				.setScale(scale, java.math.BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		// 格式化数据，小数点尾数不够的后面补零，例如：1.1 若保留四位的话，则显示：1.1000
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.000000");
		String resultRound = df.format(dResult);
		// 返回结果值
		return new Double(resultRound);
	}

	/**
	 * 转换成String（检索条件）
	 * 
	 * @param obj
	 */
	public static String convertString(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return null;
		}
	}

	/**
	 * 截取日期String
	 * 
	 * @param date
	 */
	public static String substrDate(String date) {
		if (date != null && !date.equals("")) {
			return date.substring(0, 8);
		} else {
			return null;
		}
	}

	/**
	 * String转成Double
	 * 
	 * @param num
	 */
	public static double strToDouble(String num) {
		return Double.parseDouble(num.replaceAll(",", ""));
	}

	/**
	 * 转成int
	 * 
	 * @param num
	 */
	public static int convertint(Object o) {

		if (o != null && !o.equals("")) {
			return Integer.parseInt(o.toString());
		} else {
			return 0;
		}
	}

	/**
	 * 转成Integer
	 * 
	 * @param num
	 */
	public static Integer convertInteger(Object o) {

		if (o != null && !o.equals("")) {
			return new Integer(o.toString());
		} else {
			return null;
		}
	}

	/**
	 * 单个数字转变成大写
	 * 
	 * @param num
	 * @return
	 */
	public static String changeNumBig(String num) {

		if (num == null)
			num = "";
		if (num.equals("0"))
			num = "零";
		if (num.equals("1"))
			num = "壹";
		if (num.equals("2"))
			num = "贰";
		if (num.equals("3"))
			num = "叁";
		if (num.equals("4"))
			num = "肆";
		if (num.equals("5"))
			num = "伍";
		if (num.equals("6"))
			num = "陆";
		if (num.equals("7"))
			num = "柒";
		if (num.equals("8"))
			num = "捌";
		if (num.equals("9"))
			num = "玖";

		return num;
	}

	/**
	 * 字符串大写金额，格式化输出
	 * 
	 * @param money
	 * @return
	 */
	public static String changeNum(String money) {

		String[] num = new String[money.length()];

		for (int t = 0; t < money.length(); t++) {
			num[t] = changeNumBig(money.substring(money.length() - t - 1, money
					.length()
					- t));
			if (t > 0 && num[t].equals("零") && num[t - 1].equals("零")) {
				num[t - 1] = "";
			}
		}

		String[] list = new String[3 * num.length];
		for (int i = 0; i < list.length; i++) {
			list[i] = "";
		}

		list[0] = num[0];

		if (num.length > 1 && !num[1].equals("")) {
			if (!num[1].equals("零")) {
				list[1] = "拾";
			}
			list[2] = num[1];
		}
		if (num.length > 2 && !num[2].equals("")) {
			if (!num[2].equals("零")) {
				list[3] = "佰";
			}
			list[4] = num[2];
		}
		if (num.length > 3 && !num[3].equals("")) {
			if (!num[3].equals("零")) {
				list[5] = "仟";
			}
			list[6] = num[3];
		}
		if (num.length > 4 && !num[4].equals("")) {
			if (!num[4].equals("零")) {
				list[7] = "万";
			}
			list[8] = num[4];
		}
		if (num.length > 5 && !num[5].equals("")) {
			if (!list[7].equals("万") && !num[5].equals("零")) {
				list[9] = "万";
			}
			if (!num[5].equals("零"))
				list[10] = "拾";
			list[11] = num[5];
		}
		if (num.length > 6 && !num[6].equals("")) {
			if (!list[7].equals("万") && !list[9].equals("万")
					&& !num[6].equals("零")) {
				list[12] = "万";
			}
			if (!num[6].equals("零"))
				list[13] = "佰";
			list[14] = num[6];
		}
		if (num.length > 7 && !num[7].equals("")) {
			if (!list[7].equals("万") && !list[9].equals("万")
					&& !list[12].equals("万") && !num[7].equals("零")) {
				list[15] = "万";
			}
			if (!num[7].equals("零"))
				list[16] = "仟";
			list[17] = num[7];
		}
		if (num.length > 8 && !num[8].equals("")) {
			if (!num[8].equals("零"))
				list[18] = "亿";
			list[19] = num[8];
		}
		if (num.length > 9 && !num[9].equals("")) {
			if (!list[18].equals("亿") && !num[9].equals("零")) {
				list[20] = "亿";
			}
			if (!num[9].equals("零"))
				list[21] = "拾";
			list[22] = num[9];
		}
		if (num.length > 10 && !num[10].equals("")) {
			if (!list[18].equals("亿") && !list[20].equals("亿")
					&& !num[10].equals("零")) {
				list[23] = "亿";
			}
			if (!num[10].equals("零"))
				list[24] = "佰";
			list[25] = num[10];
		}
		if (num.length > 11) {
			if (!list[18].equals("亿") && !list[20].equals("亿")
					&& !list[23].equals("亿") && !num[11].equals("零")) {
				list[26] = "亿";
			}
			if (!num[11].equals("零"))
				list[27] = "仟";
			list[28] = num[11];
		}
		if (num.length > 12) {
			for (int l = 12; l < num.length; l++) {
				list[32 + l - 12] = num[l];
			}
		}

		String reList = "";

		for (int s = list.length - 1; s > -1; s--) {
			if (!list[s].equals("")) {
				@SuppressWarnings("unused")
				int k = 0;
				reList = reList + list[s];
				k++;
			}
		}
		if (reList.substring(reList.length() - 1, reList.length()).equals("零")) {
			reList = reList.substring(0, reList.length() - 1);
		}
		return reList;
	}

	/**
	 * 日期加减
	 * 
	 * @param money
	 * @return
	 */
	public static Date dateAdd(Date d, int n) {

		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, n);
		return c.getTime();

	}

	/**
	 * 取得日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastMonthDay(String t) {

		Calendar lCal = Calendar.getInstance();
		String year = t.substring(0, 4);
		String month = t.substring(3, 6);
		lCal.set(Integer.parseInt(year), Integer.parseInt(month), 1);// 将字符串转换成整型。

		lCal.add(Calendar.MONTH, 0);
		lCal.add(Calendar.DATE, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(lCal.getTime());
	}

	 

	/**
	 * 得到类名
	 */
	public static String getClassName(String str) {
		String lowerStr = str.toLowerCase();
		int index = lowerStr.indexOf("_");
		lowerStr = lowerStr.replaceFirst("_", "");
		lowerStr = lowerStr.substring(index, index + 1).toUpperCase()
				+ lowerStr.substring(index + 1);
		while (lowerStr.indexOf("_") != -1) {
			index = lowerStr.indexOf("_");
			lowerStr = lowerStr.replaceFirst("_", "");
			lowerStr = lowerStr.substring(0, index)
					+ lowerStr.substring(index, index + 1).toUpperCase()
					+ lowerStr.substring(index + 1);
		}
		return getFirstUpper(lowerStr);
	}
	/**
	 * 第一个字母大写
	 */
	public static String getFirstUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	/**
	 * 格式化日期
	 * @param d
	 * @param format
	 * @return
	 */
	public static String formatDate(Date d, String format) {
		if (d == null) {
			return "";
		} else {
			return new SimpleDateFormat(format).format(d);
		}
	}
	/**
	 * 横线前第一个字母大写
	 */
	public static String getHorizontalLineUpper(String str) {
		String lowerStr = str.toLowerCase();
		while (lowerStr.indexOf("_") != -1) {
			int index = lowerStr.indexOf("_");
			lowerStr = lowerStr.replaceFirst("_", "");
			lowerStr = lowerStr.substring(0, index)
					+ lowerStr.substring(index, index + 1).toUpperCase()
					+ lowerStr.substring(index + 1);
		}
		return lowerStr;
	}
	/**
	 * 生成UUid
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
 
	public static int getRandomNumberOfFour(){
		return (int)(Math.random()*10000);
	}
	
	/**
	 * 获取IP地址
	 * @return 
	 * @throws Exception
	 */
	public static String getIpAddr2(HttpServletRequest request) {  
	       String ip = request.getHeader("x-forwarded-for");  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getHeader("Proxy-Client-IP");  
	       }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getHeader("WL-Proxy-Client-IP");  
	       }  
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	           ip = request.getRemoteAddr();  
	       }  
	       return ip;  
	 } 
	
	
	/**
	 * 获取IP地址
	 * @return 
	 * @throws Exception
	 */
	 public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        String _ip="";
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
        	_ip=ip;
        }else{
          ip = request.getHeader("X-Forwarded-For");
          if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
            	_ip=ip.substring(0, index);
            } else {
            	_ip=ip;
            }
          } else {
        	  _ip=request.getRemoteAddr();
          }
        }
        if("0:0:0:0:0:0:0:1".equals(_ip)){
        	_ip="127.0.0.1";
        }
        return _ip;
        
    }
	
		
		/**
		 * 将逗号分隔的字符串转化成字符串list
		 * @param strs
		 * @return 若strs为空，返回size为零的list
		 */
		public static List<String> stringtoList(String strs){
			List<String> list=new ArrayList<String>();
			if(ToolUtils.isNotEmpty(strs)){
				String[] starr=strs.split(",");
				for (int i = 0; i < starr.length; i++) {
					list.add(starr[i]);
				}
			}
			return list;
		}
		
		
		
		
	public static void main(String[] args) {
		String formatDate = formatDate(new Date(), "yyyyMMdd");
		System.out.println(formatDate);
	}
	
	/**
	 * 已utf-8编码进行base64加密
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByBASE64(String data) throws UnsupportedEncodingException{
		return ToolUtils.encodeByBASE64(data,"utf-8");
	}
	
	/**
	 * base64加密
	 * @param data 原数据
	 * @param encoding 编码格式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByBASE64(String data,String encoding) throws UnsupportedEncodingException{
		if(!ToolUtils.isNotEmpty(data)){
			return "";
		}
		
		return ToolUtils.encodeByBASE64(data.getBytes(encoding));
	}
	
	/**
	 * base64加密
	 * @param data
	 * @return
	 */
	public static String encodeByBASE64(byte[] data){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
	/**
	 * 通过BASE64进行解码
	 * @param data 数据
	 * @return
	 * @throws IOException
	 */
	public static byte[] decodeByBASE64(String data) throws IOException{
		if(!ToolUtils.isNotEmpty(data)){
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(data);
	}
	/**
	 * 通过指定编码进行base64解码
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String decodeByBASE64(String data,String encoding) throws IOException{
		if(!ToolUtils.isNotEmpty(data)){
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(data),encoding);
	}

}
