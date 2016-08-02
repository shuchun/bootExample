package com.example.base;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * RSA，DSA,MD5加密、解密、验签工具类
 * @author ysc
 *
 */
public class EncryptUtil {
	
	private static final String ALGORITHM="RSA";//加密算法
	private static final int KEYSIZE=1024;//密钥长度
	private static final int EN_MAX_SIZE=117;//加密最大长度
	private static final int DE_MAX_SIZE=128;//解密最大长度

	
	//存储key pair的map的key
	public final static String PRIVATE_KEY="prvKey";
	public final static String PUBLIC_KEY="pubKey";
	
	
	/**
	 * 生成密钥对
	 * @param  algorithm 算法 ，默认使用RSA
	 * @throws NoSuchAlgorithmException 
	 */
	public static Map<String, String> getKeyPairMap(String algorithm) throws Exception{
		
		if(!Tools.isNotEmpty(algorithm)){
			algorithm=ALGORITHM;
		}
		
		KeyPairGenerator generator=KeyPairGenerator.getInstance(algorithm);//根据算法初始化密钥对
		generator.initialize(KEYSIZE);//根据大小初始化
		
		//产生密钥对
		KeyPair keyPair=generator.generateKeyPair();
		
		//得到公钥
		Key publicKey=keyPair.getPublic();
		//得到私钥
		Key privateKey=keyPair.getPrivate();
		//System.out.println(Tools.encodeByBASE64(publicKey.getEncoded()));
		//System.out.println(Tools.encodeByBASE64(privateKey.getEncoded()));

		Map<String,String> keyPairMap=new HashMap<String,String>();
		keyPairMap.put(PRIVATE_KEY, Tools.encodeByBASE64(privateKey.getEncoded()));
		keyPairMap.put(PUBLIC_KEY, Tools.encodeByBASE64(publicKey.getEncoded()));
		
		return keyPairMap;
	}
	
	/**
	 * 生成密钥对
	 * @param  algorithm 算法 ，默认使用RSA
	 * @throws NoSuchAlgorithmException 
	 */
	public static KeyPair getKeyPair(String algorithm) throws Exception{
		
		if(!Tools.isNotEmpty(algorithm)){
			algorithm=ALGORITHM;
		}
		
		KeyPairGenerator generator=KeyPairGenerator.getInstance(algorithm);//根据算法初始化密钥对
		generator.initialize(KEYSIZE);//根据大小初始化
		
		//产生密钥对
		KeyPair keyPair=generator.generateKeyPair();
		
		return keyPair;
	}

	/**
	 * RSA加密方法
	 * @param publicKey 公钥文件路径
	 * @param source		明文信息
	 * @param algorithm		加密算法
	 * @throws Exception 
	 */
	public static String encryptByRSA(Key publicKey,String source,String algorithm) throws Exception{

		//用公钥字串加密
		//RSAPublicKey rsaPublicKey=loadPublicKeyByStr(PUBLIC_KEY_STR);
		Cipher cipher = Cipher.getInstance(algorithm);
        //cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        

        byte[] msg = source.getBytes();
        /** 执行加密操作 */

        int offset=0;
        int inputLen=msg.length;
        int i=0;
        /** 执行加密操作 */
        byte[] cache ;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //分段加密
        while(inputLen-offset>0){
        	if(inputLen-offset>EN_MAX_SIZE){//没有加密完
        		cache=cipher.doFinal(msg, offset, EN_MAX_SIZE);
        	}else{//加密完成
        		cache=cipher.doFinal(msg, offset, inputLen-offset);
        	}
        	i++;
        	offset=i*EN_MAX_SIZE;
        	out.write(cache,0,cache.length);
        }
       
        String cry=Tools.encodeByBASE64(out.toByteArray());

        return cry;
	}

	/**
	 * DES加密方法
	 * @param key		密钥
	 * @param source	明文
	 * @param algorithm	算法
	 * @return
	 */
	public static String encryptByDES(String key,String source,String algorithm){
		
		// 如果没有传递算法实例，默认使用如下算法
		if (algorithm == null || algorithm.length() < 1) {
			// 算法/模式/填充规则
			algorithm = "DES/ECB/ZeroBytePadding";
		}

		StringBuilder result = new StringBuilder();
		try {
			// 关键第三方库，用于支持默认JDK不支持的填充算法
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			byte[] sourceByte = source.getBytes();// 明文字节量
			byte[] bkey = key.getBytes();// 密钥字节量
			byte[] retkey = new byte[8];// 真正的密钥字串
			for (int i = 1; i <= bkey.length; i++) {
				retkey[i % 8] = (byte) (retkey[i % 8] + bkey[i - 1]);// 根据密钥字节量生成密钥
			}
			Cipher cipher = Cipher.getInstance(algorithm);// 根据算法获取加密机

			DESKeySpec desKeySpec = new DESKeySpec(retkey);// 根据指定的密钥生成DESkey

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获取密钥工厂
			// 密钥
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);// 置入密钥
			// 偏移量
			// IvParameterSpec iv = new IvParameterSpec(retkey);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化加密机

			byte[] encryStr = cipher.doFinal(sourceByte);// 加密明文

			for (int i = 0; i < encryStr.length; i++) {
				result.append(String.format("%02X", encryStr[i]));// 将加密后的字节转换为16进制字串
			}
		} catch (Exception e) {
			System.out.println("加密失败");
			e.printStackTrace();
		}
		return result.toString();
	}	
	
	@SuppressWarnings("restriction")
	public static String decryptByDES(String cryptData) {  
        String decryptedData = null;  
        try {  
        	String source="161";
        	byte[] bkey = source.getBytes();
			byte[] retkey = new byte[8];
			for (int i = 1; i <= bkey.length; i++)
			{
				retkey[i % 8] = (byte)(retkey[i % 8] + bkey[i-1]);
			}
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();  
            DESKeySpec deskey = new DESKeySpec(retkey);  
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(deskey);  
            // 解密对象  
            Cipher cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.DECRYPT_MODE, key, sr);  
            // 把字符串解码为字节数组，并解密  
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));  
        } catch (Exception e) {  
           e.printStackTrace();
        }  
        return decryptedData;  
    }  
	
	/**
	 * dsa加密签名
	 * @param privateKeyFile		私钥文件
	 * @param content				加密内容
	 * @param algorithm			算法
	 * @throws Exception
	 */
	public static String encryptByDSA(String privateKeyFile,String content,String algorithm) throws Exception{
		if(!Tools.isNotEmpty(algorithm)){
			algorithm=ALGORITHM;
		}
		
		PrivateKey privateKey;
		ObjectInputStream ois=null;
		
		try {
            /** 将文件中的私钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(privateKeyFile));
            privateKey = (PrivateKey) ois.readObject();
        } catch (Exception e) {
            throw e;
        }
        finally{
            ois.close();
        }
		
		Signature signature=Signature.getInstance(algorithm);
		signature.initSign(privateKey);
		signature.update(content.getBytes());
		byte[] cry=signature.sign();

		return Tools.encodeByBASE64(cry);
	}
	
    /**
     * RSA解密算法
     * @param privateKey 私钥文件路径
     * @param cryptograph    密文
     * @param algorithm 	 解密算法,默认使用RSA算法
     * @throws Exception
     */
    public static String decryptByRSA(Key privateKey,String cryptograph,String algorithm) throws Exception {
    	
    	//用私钥字符串解密内容
    	//RSAPrivateKey rsaPrivateKey=loadPrivateKeyByStr(PRIVATE_KEY_STR);
    	Cipher cipher = Cipher.getInstance(algorithm);
        //cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
    	cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] msg=Tools.decodeByBASE64(cryptograph);

        int offset=0;
        int inputLen=msg.length;
        int i=0;
        /** 执行解密操作 */
        byte[] cache ;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //分段解密
        while(inputLen-offset>0){
        	if(inputLen-offset>DE_MAX_SIZE){//没有解析完
        		cache=cipher.doFinal(msg, offset, DE_MAX_SIZE);
        	}else{//解析完成
        		cache=cipher.doFinal(msg, offset, inputLen-offset);
        	}
        	i++;
        	offset=i*DE_MAX_SIZE;
        	out.write(cache,0,cache.length);
        }

        
        return new String(out.toByteArray());
    }
    
    /**
     * RSA解密算法
     * @param privateKey 私钥文件路径
     * @param cryptograph    密文
     * @param algorithm 	 解密算法,默认使用RSA算法
     * @throws Exception
     */
    public static String decryptByRSA(PrivateKey privateKey,String cryptograph,String algorithm) throws Exception {
    	
    	//用私钥字符串解密内容
    	Cipher cipher = Cipher.getInstance(algorithm);
    	cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] msg=Tools.decodeByBASE64(cryptograph);

        int offset=0;
        int inputLen=msg.length;
        int i=0;
        /** 执行解密操作 */
        byte[] cache ;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //分段解密
        while(inputLen-offset>0){
        	if(inputLen-offset>DE_MAX_SIZE){//没有解析完
        		cache=cipher.doFinal(msg, offset, DE_MAX_SIZE);
        	}else{//解析完成
        		cache=cipher.doFinal(msg, offset, inputLen-offset);
        	}
        	i++;
        	offset=i*DE_MAX_SIZE;
        	out.write(cache,0,cache.length);
        }

        
        return new String(out.toByteArray());
    }
    
    /**
     * DSA验签方法
     * @param publicKeyFile 公钥文件
     * @param sigStr	内容
     * @param content 	明文字串
     * @param algorithm 算法
     * @return 是否验签成功
     * @throws Exception
     */
	public static boolean validByDSA(String publicKeyFile,String sigStr,String content,String algorithm) throws Exception{
		if(!Tools.isNotEmpty(algorithm)){
			algorithm=ALGORITHM;
		}
		
		PublicKey publicKey;
		ObjectInputStream ois=null;
		
		try {
            /** 将文件中的公钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(publicKeyFile));
            publicKey = (PublicKey) ois.readObject();
        } catch (Exception e) {
            throw e;
        }
        finally{
            ois.close();
        }
		
		byte[] sigMsg=Tools.decodeByBASE64(sigStr);
		
		Signature signature=Signature.getInstance(algorithm);
		signature.initVerify(publicKey);
		signature.update(content.getBytes());
		
		 return signature.verify(sigMsg); 
	}
	
	/**
	 * RSA签名
	 * @param signatureAlgorithm 签名算法
	 * @param data 明文数据
	 * @param prvKey 私钥字串或者私钥证书
	 * @param useCertificate 使用证书，
	 * 		      如果为true在把privateKey参数做为一个私钥证书路径，为false时把privatekey当作一个私钥字串
	 * @throws Exception
	 */
	public static String signByRSA(String signatureAlgorithm, String data,String prvKey,boolean useCertificate)
			throws Exception {
		
		//加载私钥
		//RSAPrivateKey privateKey=loadPrivateKeyByStr(PRIVATE_KEY_STR);
		PrivateKey privateKey=null;
		if(useCertificate){//使用私钥证书
			privateKey=loadPrivateKeyByFile(prvKey);
		}else{
			privateKey=loadPrivateKeyByStr(prvKey);
		}
		// 实例化Signature
		Signature signature = Signature.getInstance(signatureAlgorithm);
		// 初始化Signature
		signature.initSign( privateKey);
		byte[] signData = data.getBytes("utf-8");
		signature.update(signData);

		return Tools.encodeByBASE64(signature.sign());
	}
	
	/**
	 * 签名url，由于url中不能含有‘/’所以需要使用encodeBase64URLSafeString方法把签名加密
	 * @param algorithm 加密算法
	 * @param signatureAlgorithm 算法
	 * @param data 明文
	 * @param pubKey 公钥文件或公钥字串
	 * @param useCertificate 使用文件还是字串，true:pubKey参数为文件路径，false：pubKey为字符串
	 * @throws Exception
	 */
	public static String signUrl(String algorithm, String signatureAlgorithm, String data,String pubKey,boolean useCertificate)
			throws Exception {
		//加载公钥
		PublicKey publicKey=null;
		if(useCertificate){//使用文件
			publicKey= loadPublicKeyByFile(pubKey);
		}else{
			publicKey= loadPublicKeyByStr(pubKey);
		}
		//初始化实例
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		byte[] encryptByte = cipher.doFinal(data.getBytes("UTF-8"));
		return Base64.encodeBase64URLSafeString(encryptByte);
	}

	/**
	 * 
	 * @param signatureAlgorithm 解签名算法
	 * @param source  明文
	 * @param sign  密文
	 * @param pubKey 公钥字串或公钥证书
	 * @param useCertificate 使用证书，ture：pubKey参数传递公钥证书路径，false:pubKey参数传递公钥字串
	 * @throws Exception
	 */
	public static boolean varifyByRSA( String signatureAlgorithm,String source,String sign,String pubKey,boolean useCertificate) throws Exception{
		//RSAPublicKey rsaPublicKey=loadPublicKeyByStr(PUBLIC_KEY_STR);
		
		PublicKey publicKey=null;
		if(useCertificate){//使用公钥文件
			publicKey=loadPublicKeyByFile(pubKey);
		}else{//使用公钥字串
			publicKey=loadPublicKeyByStr(pubKey);
		}
		
		// 实例化Signature
		Signature signature = Signature.getInstance(signatureAlgorithm);
		// 初始化Signature
		signature.initVerify(publicKey);
		signature.update(source.getBytes());

		return signature.verify(Tools.decodeByBASE64(sign));
	}
	
	/**
	 * 从文件中读取公钥
	 * @param publicKeyFile		公钥文件
	 * @throws IOException
	 */
	public static PublicKey loadPublicKeyByFile(String publicKeyFile) throws IOException{
		PublicKey publicKey = null;
		ObjectInputStream ois=null;
		publicKeyFile=EncryptUtil.class.getResource(publicKeyFile).getPath();
		try {
            // 将文件中的公钥对象读出 
            ois = new ObjectInputStream(new FileInputStream(publicKeyFile));
            publicKey = (PublicKey) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            ois.close();
        }
		
		return publicKey;
	}
	
	/**
	 * 从文件中读取私钥
	 * @param privateKeyFile		私钥文件
	 * @throws IOException
	 */
	public static PrivateKey loadPrivateKeyByFile(String privateKeyFile) throws IOException{
		PrivateKey privateKey = null;
        ObjectInputStream ois = null;
        privateKeyFile=EncryptUtil.class.getResource(privateKeyFile).getPath();
        try {
            // 将文件中的私钥对象读出 
            ois = new ObjectInputStream(new FileInputStream(privateKeyFile));
            privateKey = (PrivateKey) ois.readObject();
        } catch (Exception e) {
        	System.out.println("获取私钥出错");
            e.printStackTrace();
        }
        finally{
            ois.close();
        }
        
        return privateKey;
	}
	
	/** 
     * 从字符串中加载公钥 
     *  
     * @param publicKeyStr 
     *            公钥数据字符串 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)  
            throws Exception {  
        try {  
        	byte[] buffer = Tools.decodeByBASE64(publicKeyStr);  
        	//byte[] buffer = (new BASE64Decoder()).decodeBuffer( publicKeyStr);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    } 
	
    /** 
     * 从字符串中加载私钥
     *  
     * @param privateKeyStr
     *            私钥数据字符串 
     * @throws Exception 
     *             加载私钥时产生的异常 
     */ 
	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)  
            throws Exception {  
        try {  
        	byte[] buffer =Tools.decodeByBASE64(privateKeyStr);  
        	//byte[] buffer =(new BASE64Decoder()).decodeBuffer(privateKeyStr);  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("私钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥数据为空");  
        }  
    }  
   
	/**
	 * md5签名，默认签名后转换为大写
	 * @param signatureStr 待签名字串
	 */
	public static String encryptByMD5(String signatureStr){
		return EncryptUtil.encryptByMD5(signatureStr, true);
	}
	
	/**
	 * md5签名
	 * @param signatureStr 待签名字串
	 * @param toUpperCase  是否将签名后字串转换为大写
	 */
	public static String encryptByMD5(String signatureStr,boolean toUpperCase){
		if(toUpperCase){
			return MD5.MD5Encode(signatureStr).toUpperCase();
		}else{
			return MD5.MD5Encode(signatureStr);
		}
		
	}
	
	/**
	 * md5签名数据流
	 * @param stream	输入流
	 * @return	加密字串
	 */
	public static String encryptStreamByMD5(InputStream stream){
		return EncryptUtil.encryptStreamByMD5(stream, true);
	}
	
	/**
	 * md5签名数据流
	 * @param stream		输入流
	 * @param toUpperCase	是否将结果转换为大写格式
	 * @return	加密字串
	 */
	public static String encryptStreamByMD5(InputStream stream,boolean toUpperCase){
		if(toUpperCase){
			return MD5.MD5Stream(stream).toUpperCase();
		}else{
			return MD5.MD5Stream(stream);
		}
		
	}
	
	/**
	 * SHA签名数据流
	 * @param stream	输入流
	 */
	public static String encryptStreamBySHA(InputStream stream){
		
		return EncryptUtil.encryptStreamBySHA(stream, false);
	}
	
	/**
	 * SHA签名数据流
	 * @param stream		输入流
	 * @param toUpperCase	是否将加密串转为大写
	 */
	public static String encryptStreamBySHA(InputStream stream,boolean toUpperCase){
		String resultString=null;
		try {
			resultString= DigestUtils.sha256Hex(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(toUpperCase&&Tools.isNotEmpty(resultString)){
			resultString=resultString.toUpperCase();
		}
		
		return resultString;
	}
	
	/**
	 * 本系统的属性加密算法，自定义
	 * @param property		属性
	 */
	public static String encryptProperty(String property){
		String sign="";
		try {
			sign = Tools.encodeByBASE64(property);
			sign=Tools.encodeByBASE64(sign);
		} catch (UnsupportedEncodingException e) {
			System.out.println("属性签名失败");
			e.printStackTrace();
		}
		
		
		return sign;
	}
	
	/**
	 * 本系统的属性解密算法，自定义
	 * @param signStr			密钥串
	 * @return
	 */
	public static String decryptProperty(String signStr){
		String source="";
		try {
			source = new String(Tools.decodeByBASE64(signStr));
			source=new String(Tools.decodeByBASE64(source));
		} catch (IOException e) {
			System.out.println("属性解密失败");
			e.printStackTrace();
		}
		
		
		return source;
	}
	
	
    /**
     * 测试方法
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
   
    	//final String DSA="DSA";
    	//final String RSA="RSA";
    	
    	//generateKeyPair(RSA);
    	//System.out.println("生成公私钥完毕");
    	
    	 /*String source = "恭喜发财!";// 要加密的字符串
    	
    	String cryptograph = encrypt(source,RSA);// 生成的密文
        System.out.print("用公钥加密后的结果为:" + cryptograph);
        System.out.println();
        
       String target = String.valueOf(validByDSA(PUBLIC_KEY_FILE,cryptograph,source,DSA));// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();*/
    	
    	
    	//String source = "恭喜发财!";// 要加密的字符串
        //System.out.println("准备用公钥加密的字符串为：" + source);
        
     /*   String cryptograph = encryptByRSA(PUBLIC_KEY_FILE,source,RSA);// 生成的密文
        System.out.print("用公钥加密后的结果为:" + cryptograph);
        System.out.println();

        String target = decryptByRSA(PRIVATE_KEY_FILE,cryptograph,RSA);// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();*/
     /*   String sign=sign("RSA", "SHA1withRSA",source.toString());
        System.out.println(sign);
        boolean vlid=varify("SHA1withRSA",source,sign);
        System.out.println(vlid);*/
    	
    	//String sign="AueLO0xDHmRskz9lQgVE7VuNy/96dkLBWtMk7kKBdkH0hPl2kl8efynNWVyZY3O7MIFoApuGf4vFgqnpEKlGkShup15SNlfdLXryX35Vmv7h1FqZX1uLSygRnY5zZOsMNRbN0ka9NgBG0XvTP/vjVMQ2Ke7tjWTcIVnQUrkjvj9WlM7vj4B2t+fCTe6ktRIfOqXkYmVMYLwoZybiJ6XPYt9l3/ESHnTT/xRXnIyr/RGlzkONOAPPgNZmqWrX3619DYfVLBbwSe+5+Hzpbgbi+nIdKcf27C5UraCgXlrG8wQbP9S5a7e/gTbU7G6T6eeJZhtDq1pncaNMq8AZNnRYSQ==";
    	//String source="UREG3000000036fbc72acb8cd1471a98ac74e64e212555B0120150724000000081ICBC2015-07-24 15:45:56";
    	//boolean valid =EncryptUtil.varifyByRSA("SHA1withRSA", source, sign, ResourceUtil.getCerFile(), true);
    	//boolean valid =EncryptUtil.varifyByRSA("SHA1withRSA", source, sign,PUBLIC_KEY_STR , false);
    	//System.out.println(valid);
    	
    	/*String s="1zh_CNfbc72acb8cd1471a98ac74e64e212555UREG21437718642251";
    	String aa=EncryptUtil.signByRSA("SHA1withRSA",s.toString(), PRIVATE_KEY_STR, false);
        System.out.println(aa);*/
    }

}
