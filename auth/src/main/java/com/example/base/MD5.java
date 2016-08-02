package com.example.base;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @ClassName: MD5
 * @Description: MD5加密类
 * @author: ysc
 *
 */
public class MD5 {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] originByte=resultString.getBytes("utf-8");
            resultString = byteArrayToHexString(md.digest(originByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    /**
     * MD5对流文件内容编码
     * @param stream 文件输入流
     * @return 加密结果
     */
    public static String MD5Stream(InputStream stream){
    	String resultString=null;
    	try {
			resultString=DigestUtils.md5Hex(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return resultString;
    }

}
