package com.example.base;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBM on 2016/8/1.
 * 通用工具类
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
     * @param data      数据
     * @return          密文
     * @throws UnsupportedEncodingException
     */
    public static String encodeByBASE64(String data) throws UnsupportedEncodingException{
        return Tools.encodeByBASE64(data,"utf-8");
    }

    /**
     * base64加密
     * @param data      原数据
     * @param encoding  编码格式
     * @return          密文
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
     * @param data  数据
     * @return      密文
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
     * 解密
     * @param data  密文数据
     * @return      明文
     * @throws IOException
     */
    public static String decodeByBASE64(String data,String encoding) throws IOException {
        if(!Tools.isNotEmpty(data)){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(data),encoding);
    }

    /**
     * 对象转换为Map
     * @param data          数据
     * @param enableNull    是否添加null对象值
     * @return              map
     */
    public static Map<String, Object> toMap(Object data,boolean enableNull) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj;
            try {
                obj = field.get(data);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }else if(enableNull) {
                    map.put(field.getName(),null);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
}
