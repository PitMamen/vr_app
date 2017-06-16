package com.mygt.vrapp;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Des3 {
	// 密钥
	private final static String secretKey = "1E623472A8B506CE880443FB36EDE0B2";
	// 向量
	private final static String iv = "2017_APP";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) {
		try {
//			if(!FinalUtil.isEncrypt){
//				return plainText;
//			}
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return com.mygt.vrapp.Base64.encode(encryptData);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText){
		try {
//			if(!FinalUtil.isEncrypt){
//				return encryptText;
//			}
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

			byte[] decryptData = cipher.doFinal(Base64.decode(encryptText,0));

			return new String(decryptData, encoding);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
//
//	public static void main(String[] args) {
//		try {
//			String s = String.valueOf(System.currentTimeMillis())
//					+ new UNIDGenerate().toString();
//			String e = Des3.encode(s);
//			String d = Des3
//					.decode(e);
//			System.out.println(s);
//			System.out.println(e);
//			System.out.println(d);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
