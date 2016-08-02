package com.example.base;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by IBM on 2016/8/1.
 */
public class Tools {
    /**
     * 判断对象不是空的集合或者不是空的字符串或空格
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Collection<?>){
            return obj!=null&&((Collection<?>) obj).size()>0;
        }else if(obj instanceof Map<?, ?>){
            return obj!=null&&((Map<?, ?>) obj).size()>0;
        }else {
            return obj != null && !"".equals(obj.toString().trim());
        }
    }

    /**
     * 已utf-8编码进行base64加密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeByBASE64(String data) throws UnsupportedEncodingException{
        return Tools.encodeByBASE64(data,"utf-8");
    }

    /**
     * base64加密
     * @param data 原数据
     * @param encoding 编码格式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeByBASE64(String data,String encoding) throws UnsupportedEncodingException {
        if(!Tools.isNotEmpty(data)){
            return "";
        }

        return Tools.encodeByBASE64(data.getBytes(encoding));
    }

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
        if(!Tools.isNotEmpty(data)){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(data);
    }
    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static String decodeByBASE64(String data,String encoding) throws IOException {
        if(!Tools.isNotEmpty(data)){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(data),encoding);
    }
}
